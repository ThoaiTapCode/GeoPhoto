# ⚡ Quick Start Guide

Hướng dẫn nhanh để chạy GeoPhoto trong 3 phút!

## 🐳 Với Docker (Khuyến nghị - 1 lệnh!)

```bash
# Clone và chạy
git clone <repository-url>
cd PhotoMap-demo
docker-compose up -d

# Mở trình duyệt: http://localhost
```

**Xong!** 🎉

## 💻 Không dùng Docker

```bash
# 1. Clone
git clone <repository-url>
cd PhotoMap-demo

# 2. Khởi động MongoDB
mongod --dbpath=./data/db

# 3. Backend (Terminal 1)
cd backend
mvn spring-boot:run

# 4. Frontend (Terminal 2)
cd frontend
npm install
npm run dev

# 5. Mở trình duyệt: http://localhost:5173
```

## 📝 Lần đầu sử dụng

1. Truy cập ứng dụng
2. Đăng ký tài khoản mới
3. Upload ảnh có GPS
4. Xem ảnh trên bản đồ!

## ❓ Cần giúp đỡ?

- Xem [README.md](README.md) để biết chi tiết
- Xem [DOCKER.md](DOCKER.md) cho hướng dẫn Docker
- Xem [SETUP.md](SETUP.md) cho troubleshooting

