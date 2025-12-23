package com.geophoto.service;

import com.geophoto.dto.RegisterRequest;
import com.geophoto.entity.User;
import com.geophoto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Authentication Service
 * Handles user registration and authentication logic
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    /**
     * Register a new user
     * @param request RegisterRequest containing user information
     * @return User entity
     * @throws RuntimeException if username or email already exists
     */
    @Transactional
    public User register(RegisterRequest request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username đã được sử dụng");
        }
        
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng");
        }
        
        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setEnabled(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(User.Role.USER);
        
        User savedUser = userRepository.save(user);
        log.info("User registered successfully: {}", savedUser.getUsername());
        
        return savedUser;
    }
    
    /**
     * Find user by username
     * @param username Username to search for
     * @return User entity
     * @throws RuntimeException if user not found
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user với username: " + username));
    }
}

