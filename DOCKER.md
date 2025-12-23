# 🐳 Hướng dẫn Docker

Hướng dẫn chi tiết về cách chạy GeoPhoto với Docker.

## 📋 Yêu cầu

- **Docker** 20.10+
- **Docker Compose** 2.0+

Kiểm tra cài đặt:
```bash
docker --version
docker-compose --version
```

## 🚀 Quick Start

### Chạy Production Build

```bash
# Clone repository
git clone <repository-url>
cd PhotoMap-demo

# Build và chạy tất cả services
docker-compose up -d

# Xem logs
docker-compose logs -f

# Dừng tất cả
docker-compose down
```

**Truy cập:**
- Frontend: http://localhost (port 80)
- Backend API: http://localhost:8080
- MongoDB: localhost:27017

### Chạy Development Mode (Hot Reload)

```bash
# Chạy với hot reload cho development
docker-compose -f docker-compose.dev.yml up -d

# Xem logs
docker-compose -f docker-compose.dev.yml logs -f
```

**Truy cập:**
- Frontend: http://localhost:5173 (Vite dev server)
- Backend API: http://localhost:8080
- MongoDB: localhost:27017

## 📦 Services

### MongoDB
- **Image:** mongo:7
- **Port:** 27017
- **Volume:** `mongodb_data` (persistent data)
- **Health check:** Tự động kiểm tra sức khỏe

### Backend
- **Port:** 8080
- **Volume:** `./backend/uploads` (ảnh upload)
- **Environment variables:**
  - `SPRING_DATA_MONGODB_URI`: MongoDB connection string
  - `APP_UPLOAD_DIR`: Thư mục lưu ảnh
  - `JWT_SECRET`: Secret key cho JWT
  - `JWT_EXPIRATION`: Thời gian hết hạn token (ms)

### Frontend
- **Production:** Nginx trên port 80
- **Development:** Vite dev server trên port 5173
- **Build:** React app được build và serve qua Nginx

## 🔧 Các lệnh Docker hữu ích

### Xem logs
```bash
# Tất cả services
docker-compose logs -f

# Chỉ backend
docker-compose logs -f backend

# Chỉ frontend
docker-compose logs -f frontend

# Chỉ MongoDB
docker-compose logs -f mongodb
```

### Rebuild containers
```bash
# Rebuild tất cả
docker-compose build --no-cache

# Rebuild một service cụ thể
docker-compose build --no-cache backend
docker-compose build --no-cache frontend
```

### Restart services
```bash
# Restart tất cả
docker-compose restart

# Restart một service
docker-compose restart backend
```

### Xem status
```bash
# Xem trạng thái các containers
docker-compose ps

# Xem resource usage
docker stats
```

### Clean up
```bash
# Dừng và xóa containers
docker-compose down

# Dừng, xóa containers và volumes
docker-compose down -v

# Xóa images
docker-compose down --rmi all
```

## 🗂️ Volumes

### MongoDB Data
- **Volume name:** `mongodb_data` (production) hoặc `mongodb_data_dev` (development)
- **Location:** `/data/db` trong container
- **Persistent:** Dữ liệu được lưu giữ khi container bị xóa

### Backend Uploads
- **Path:** `./backend/uploads`
- **Mount:** `/app/uploads` trong container
- **Purpose:** Lưu trữ ảnh được upload

## 🔐 Environment Variables

### Backend

Có thể override trong `docker-compose.yml`:

```yaml
environment:
  - SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/geophoto
  - APP_UPLOAD_DIR=/app/uploads
  - JWT_SECRET=your-secret-key
  - JWT_EXPIRATION=86400000
```

Hoặc tạo file `.env`:

```env
JWT_SECRET=YourVeryLongSecretKeyHere
JWT_EXPIRATION=86400000
```

Và sử dụng trong docker-compose.yml:
```yaml
environment:
  - JWT_SECRET=${JWT_SECRET}
```

## 🐛 Troubleshooting

### Port đã được sử dụng

```bash
# Kiểm tra port nào đang được sử dụng
netstat -ano | findstr :8080  # Windows
lsof -i :8080                 # Linux/Mac

# Thay đổi port trong docker-compose.yml
ports:
  - "8081:8080"  # Thay vì 8080:8080
```

### MongoDB không kết nối được

```bash
# Kiểm tra MongoDB container
docker-compose ps mongodb

# Xem logs MongoDB
docker-compose logs mongodb

# Restart MongoDB
docker-compose restart mongodb
```

### Backend không start

```bash
# Xem logs backend
docker-compose logs backend

# Rebuild backend
docker-compose build --no-cache backend
docker-compose up -d backend
```

### Frontend không build được

```bash
# Xem logs frontend
docker-compose logs frontend

# Rebuild frontend
docker-compose build --no-cache frontend
docker-compose up -d frontend
```

### Xóa tất cả và bắt đầu lại

```bash
# Dừng và xóa tất cả
docker-compose down -v

# Xóa images
docker system prune -a

# Build lại từ đầu
docker-compose build --no-cache
docker-compose up -d
```

## 📝 Development Workflow

### 1. Development với Hot Reload

```bash
# Chạy development mode
docker-compose -f docker-compose.dev.yml up -d

# Code changes sẽ tự động reload
# Backend: Spring Boot DevTools
# Frontend: Vite HMR
```

### 2. Testing Production Build

```bash
# Build production
docker-compose build

# Chạy production
docker-compose up -d

# Test tại http://localhost
```

## 🚢 Deploy Production

### Build và tag images

```bash
# Build images
docker-compose build

# Tag images (nếu push lên registry)
docker tag geophoto-backend:latest your-registry/geophoto-backend:latest
docker tag geophoto-frontend:latest your-registry/geophoto-frontend:latest
```

### Production Recommendations

1. **Environment Variables:** Sử dụng `.env` file hoặc secrets management
2. **SSL/TLS:** Thêm reverse proxy (Nginx/Traefik) với SSL
3. **Backup:** Setup backup cho MongoDB volume
4. **Monitoring:** Thêm monitoring tools (Prometheus, Grafana)
5. **Logging:** Setup centralized logging

## 📚 Tài liệu thêm

- [Docker Documentation](https://docs.docker.com/)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [Spring Boot Docker Guide](https://spring.io/guides/gs/spring-boot-docker/)
- [React Docker Guide](https://reactjs.org/docs/getting-started.html)

