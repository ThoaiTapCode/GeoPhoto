# 🚀 Hướng dẫn Setup nhanh

Hướng dẫn từng bước để clone và chạy ứng dụng GeoPhoto.

## ⚡ Quick Start

### 1. Clone repository

```bash
git clone <repository-url>
cd PhotoMap-demo
```

### 2. Cài đặt MongoDB

**Windows:**
- Download từ: https://www.mongodb.com/try/download/community
- Cài đặt và chạy MongoDB service

**Mac (Homebrew):**
```bash
brew tap mongodb/brew
brew install mongodb-community
brew services start mongodb-community
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt-get install mongodb
sudo systemctl start mongod
```

**Docker:**
```bash
docker run -d -p 27017:27017 --name mongodb mongo:7
```

### 3. Chạy Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Đợi đến khi thấy: `Started GeoPhotoApplication`

### 4. Chạy Frontend (Terminal mới)

```bash
cd frontend
npm install
npm run dev
```

### 5. Mở trình duyệt

Truy cập: **http://localhost:5173**

## ✅ Kiểm tra

1. Backend chạy tại: http://localhost:8080
2. Frontend chạy tại: http://localhost:5173
3. MongoDB chạy tại: localhost:27017

## 🐛 Lỗi thường gặp

### MongoDB không kết nối được

```bash
# Kiểm tra MongoDB có chạy không
mongosh

# Hoặc
mongo
```

### Port đã được sử dụng

```bash
# Windows: Tìm process đang dùng port
netstat -ano | findstr :8080

# Linux/Mac: Tìm process đang dùng port
lsof -i :8080
```

### Node modules chưa cài

```bash
cd frontend
rm -rf node_modules package-lock.json
npm install
```

## 📞 Cần giúp đỡ?

Xem file README.md để biết thêm chi tiết.

