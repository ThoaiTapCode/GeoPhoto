package com.geophoto.controller;

import com.geophoto.dto.AuthResponse;
import com.geophoto.dto.LoginRequest;
import com.geophoto.dto.MessageResponse;
import com.geophoto.dto.RegisterRequest;
import com.geophoto.entity.User;
import com.geophoto.security.JwtUtils;
import com.geophoto.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication Controller
 * REST API endpoints for user authentication (register, login)
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
// CORS is configured globally in SecurityConfig, no need for @CrossOrigin here
public class AuthController {
    
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    
    /**
     * POST /api/auth/register
     * Register a new user
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        log.info("=== REGISTER ENDPOINT CALLED ===");
        log.info("Request received for username: {}", request.getUsername());
        try {
            log.info("Registration attempt for username: {}", request.getUsername());
            
            User user = authService.register(request);
            
            log.info("User registered successfully: {}", user.getUsername());
            return ResponseEntity.ok(new MessageResponse("Đăng ký thành công!"));
            
        } catch (RuntimeException e) {
            log.warn("Registration failed: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(new MessageResponse(e.getMessage()));
        } catch (Exception e) {
            log.error("Unexpected error during registration", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Lỗi không xác định khi đăng ký"));
        }
    }
    
    /**
     * POST /api/auth/login
     * Authenticate user and return JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            log.info("Login attempt for username: {}", request.getUsername());
            
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Generate JWT token
            String jwt = jwtUtils.generateJwtToken(authentication);
            
            // Get user details
            User user = (User) authentication.getPrincipal();
            
            // Create response
            AuthResponse response = new AuthResponse(
                    jwt,
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getFullName()
            );
            
            log.info("User logged in successfully: {}", user.getUsername());
            return ResponseEntity.ok(response);
            
        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            log.warn("Login failed - invalid credentials for username: {}", request.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Tên đăng nhập hoặc mật khẩu không đúng"));
        } catch (Exception e) {
            log.error("Unexpected error during login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Lỗi không xác định khi đăng nhập"));
        }
    }
    
    /**
     * GET /api/auth/me
     * Get current authenticated user information
     */
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Chưa đăng nhập"));
            }
            
            User user = (User) authentication.getPrincipal();
            
            AuthResponse response = new AuthResponse(
                    null, // Don't return token in /me endpoint
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getFullName()
            );
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("Error getting current user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Lỗi khi lấy thông tin user"));
        }
    }
}

