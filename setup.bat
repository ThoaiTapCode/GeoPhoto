@echo off
REM GeoPhoto Setup Script for Windows
REM Chạy script này để tự động setup và chạy ứng dụng

echo.
echo ========================================
echo    GeoPhoto Setup Script
echo ========================================
echo.

REM Kiểm tra các công cụ cần thiết
echo [1/4] Kiểm tra yêu cầu hệ thống...
echo.

REM Kiểm tra Java
where java >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Java chưa được cài đặt. Vui lòng cài đặt Java 17+
    pause
    exit /b 1
)
echo [OK] Java đã được cài đặt
java -version 2>&1 | findstr /i "version"

REM Kiểm tra Maven
where mvn >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Maven chưa được cài đặt. Vui lòng cài đặt Maven 3.6+
    pause
    exit /b 1
)
echo [OK] Maven đã được cài đặt
mvn --version | findstr /i "Apache Maven"

REM Kiểm tra Node.js
where node >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Node.js chưa được cài đặt. Vui lòng cài đặt Node.js 18+
    pause
    exit /b 1
)
echo [OK] Node.js đã được cài đặt
node --version

REM Kiểm tra npm
where npm >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] npm chưa được cài đặt
    pause
    exit /b 1
)
echo [OK] npm đã được cài đặt
npm --version

REM Kiểm tra MongoDB
where mongod >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [WARNING] MongoDB chưa được cài đặt hoặc không có trong PATH
    echo           Vui lòng đảm bảo MongoDB đang chạy
) else (
    echo [OK] MongoDB đã được cài đặt
    mongod --version | findstr /i "version"
)

echo.
echo [2/4] Setup Backend...
if not exist "backend\uploads" (
    mkdir backend\uploads
    echo. > backend\uploads\.gitkeep
    echo [OK] Đã tạo thư mục uploads
) else (
    echo [OK] Thư mục uploads đã tồn tại
)

echo.
echo [3/4] Setup Frontend...
cd frontend
if not exist "node_modules" (
    echo [INFO] Đang cài đặt npm packages...
    call npm install
    echo [OK] Đã cài đặt frontend dependencies
) else (
    echo [OK] Frontend dependencies đã được cài đặt
)
cd ..

echo.
echo [4/4] Setup hoàn tất!
echo.
echo ========================================
echo    Hướng dẫn chạy ứng dụng:
echo ========================================
echo.
echo 1. Khởi động MongoDB (nếu chưa chạy):
echo    mongod --dbpath=C:\data\db
echo.
echo 2. Chạy Backend (Mở Terminal/PowerShell mới):
echo    cd backend
echo    mvn spring-boot:run
echo.
echo 3. Chạy Frontend (Mở Terminal/PowerShell mới):
echo    cd frontend
echo    npm run dev
echo.
echo 4. Mở trình duyệt: http://localhost:5173
echo.
echo ========================================
pause

