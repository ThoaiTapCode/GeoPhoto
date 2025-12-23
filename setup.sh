#!/bin/bash

# GeoPhoto Setup Script
# Chạy script này để tự động setup và chạy ứng dụng

echo "🚀 GeoPhoto Setup Script"
echo "========================"
echo ""

# Kiểm tra các công cụ cần thiết
echo "📋 Kiểm tra yêu cầu hệ thống..."

# Kiểm tra Java
if ! command -v java &> /dev/null; then
    echo "❌ Java chưa được cài đặt. Vui lòng cài đặt Java 17+"
    exit 1
fi
echo "✅ Java: $(java -version 2>&1 | head -n 1)"

# Kiểm tra Maven
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven chưa được cài đặt. Vui lòng cài đặt Maven 3.6+"
    exit 1
fi
echo "✅ Maven: $(mvn --version | head -n 1)"

# Kiểm tra Node.js
if ! command -v node &> /dev/null; then
    echo "❌ Node.js chưa được cài đặt. Vui lòng cài đặt Node.js 18+"
    exit 1
fi
echo "✅ Node.js: $(node --version)"

# Kiểm tra npm
if ! command -v npm &> /dev/null; then
    echo "❌ npm chưa được cài đặt"
    exit 1
fi
echo "✅ npm: $(npm --version)"

# Kiểm tra MongoDB
if ! command -v mongod &> /dev/null; then
    echo "⚠️  MongoDB chưa được cài đặt hoặc không có trong PATH"
    echo "   Vui lòng đảm bảo MongoDB đang chạy"
else
    echo "✅ MongoDB: $(mongod --version | head -n 1)"
fi

echo ""
echo "📦 Cài đặt dependencies..."

# Setup Backend
echo "🔧 Setup Backend..."
cd backend
if [ ! -d "uploads" ]; then
    mkdir -p uploads
    touch uploads/.gitkeep
    echo "✅ Đã tạo thư mục uploads"
fi

# Setup Frontend
echo "🔧 Setup Frontend..."
cd ../frontend
if [ ! -d "node_modules" ]; then
    echo "📥 Đang cài đặt npm packages..."
    npm install
    echo "✅ Đã cài đặt frontend dependencies"
else
    echo "✅ Frontend dependencies đã được cài đặt"
fi

cd ..

echo ""
echo "✅ Setup hoàn tất!"
echo ""
echo "📝 Để chạy ứng dụng:"
echo ""
echo "1. Khởi động MongoDB (nếu chưa chạy):"
echo "   mongod --dbpath=./data/db"
echo ""
echo "2. Chạy Backend (Terminal 1):"
echo "   cd backend"
echo "   mvn spring-boot:run"
echo ""
echo "3. Chạy Frontend (Terminal 2):"
echo "   cd frontend"
echo "   npm run dev"
echo ""
echo "4. Mở trình duyệt: http://localhost:5173"
echo ""

