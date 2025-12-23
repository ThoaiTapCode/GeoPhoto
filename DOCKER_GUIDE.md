.# 🐳 Hướng dẫn chạy dự án bằng Docker

Hướng dẫn chi tiết từng bước để chạy GeoPhoto với Docker.

## 📋 Yêu cầu

Trước khi bắt đầu, đảm bảo bạn đã cài đặt:

- **Docker Desktop** (Windows/Mac) hoặc **Docker Engine** (Linux)
- **Docker Compose** (thường đi kèm với Docker Desktop)

### Kiểm tra cài đặt

```bash
# Kiểm tra Docker
docker --version
# Kết quả mong đợi: Docker version 20.10.x hoặc cao hơn

# Kiểm tra Docker Compose
docker-compose --version
# Kết quả mong đợi: docker-compose version 2.x.x
```

Nếu chưa cài đặt:
- **Windows/Mac:** Download từ https://www.docker.com/products/docker-desktop
- **Linux:** 
  ```bash
  sudo apt-get update
  sudo apt-get install docker.io docker-compose
  ```

## 🚀 Cách 1: Chạy Production Build (Khuyến nghị)

### Bước 1: Clone repository

```bash
git clone <repository-url>
cd PhotoMap-demo
```

### Bước 2: Chạy với Docker Compose

```bash
# Build và chạy tất cả services
docker-compose up -d

# Lệnh này sẽ:
# - Tải MongoDB image
# - Build backend image
# - Build frontend image
# - Khởi động tất cả containers
```

**Giải thích:**
- `up`: Khởi động containers
- `-d`: Chạy ở chế độ detached (background)

### Bước 3: Kiểm tra containers đang chạy

```bash
# Xem danh sách containers
docker-compose ps

# Kết quả mong đợi:
# NAME                    STATUS          PORTS
# geophoto-backend        Up              0.0.0.0:8080->8080/tcp
# geophoto-frontend       Up              0.0.0.0:80->80/tcp
# geophoto-mongodb        Up              0.0.0.0:27017->27017/tcp
```

### Bước 4: Xem logs (tùy chọn)

```bash
# Xem logs của tất cả services
docker-compose logs -f

# Xem logs của một service cụ thể
docker-compose logs -f backend
docker-compose logs -f frontend
docker-compose logs -f mongodb

# Nhấn Ctrl+C để thoát
```

### Bước 5: Truy cập ứng dụng

Mở trình duyệt và truy cập:

- **Frontend:** http://localhost
- **Backend API:** http://localhost:8080/api
- **MongoDB:** localhost:27017 (chỉ truy cập từ trong container)

### Bước 6: Sử dụng ứng dụng

1. Mở http://localhost trong trình duyệt
2. Đăng ký tài khoản mới
3. Upload ảnh có GPS
4. Xem ảnh trên bản đồ!

## 🔧 Cách 2: Chạy Development Mode (Hot Reload)

Development mode cho phép code thay đổi tự động reload mà không cần rebuild.

### Bước 1: Chạy development mode

```bash
docker-compose -f docker-compose.dev.yml up -d
```

### Bước 2: Truy cập ứng dụng

- **Frontend:** http://localhost:5173 (Vite dev server với hot reload)
- **Backend:** http://localhost:8080

### Bước 3: Development workflow

```bash
# Xem logs real-time
docker-compose -f docker-compose.dev.yml logs -f

# Code changes sẽ tự động:
# - Frontend: Vite HMR (Hot Module Replacement)
# - Backend: Spring Boot DevTools (nếu có)
```

## 📊 Các lệnh Docker hữu ích

### Xem trạng thái

```bash
# Xem containers đang chạy
docker-compose ps

# Xem resource usage
docker stats

# Xem chi tiết một container
docker inspect geophoto-backend
```

### Quản lý containers

```bash
# Dừng tất cả containers (giữ data)
docker-compose stop

# Khởi động lại containers
docker-compose start

# Restart một service cụ thể
docker-compose restart backend

# Dừng và xóa containers (giữ volumes)
docker-compose down

# Dừng, xóa containers và volumes (xóa data!)
docker-compose down -v
```

### Rebuild images

```bash
# Rebuild tất cả images
docker-compose build

# Rebuild không dùng cache
docker-compose build --no-cache

# Rebuild một service cụ thể
docker-compose build --no-cache backend
docker-compose build --no-cache frontend
```

### Xem logs

```bash
# Logs của tất cả services
docker-compose logs

# Logs real-time (follow)
docker-compose logs -f

# Logs của một service
docker-compose logs backend
docker-compose logs frontend
docker-compose logs mongodb

# Logs với giới hạn số dòng
docker-compose logs --tail=100 backend
```

### Truy cập vào container

```bash
# Vào trong backend container
docker-compose exec backend sh

# Vào trong frontend container
docker-compose exec frontend sh

# Vào trong MongoDB container
docker-compose exec mongodb mongosh
```

## 🗂️ Quản lý Data

### MongoDB Data

MongoDB data được lưu trong Docker volume:

```bash
# Xem volumes
docker volume ls

# Xem chi tiết volume
docker volume inspect photomap-demo_mongodb_data

# Backup MongoDB data
docker-compose exec mongodb mongodump --out=/data/backup

# Restore MongoDB data
docker-compose exec mongodb mongorestore /data/backup
```

### Upload Images

Ảnh upload được lưu trong `./backend/uploads` trên máy host:

```bash
# Xem ảnh đã upload
ls -la backend/uploads/

# Backup uploads
cp -r backend/uploads/ backups/uploads-$(date +%Y%m%d)
```

## 🐛 Troubleshooting

### Lỗi: Port đã được sử dụng

```bash
# Kiểm tra port nào đang được sử dụng
# Windows:
netstat -ano | findstr :8080

# Linux/Mac:
lsof -i :8080

# Giải pháp: Thay đổi port trong docker-compose.yml
ports:
  - "8081:8080"  # Thay vì 8080:8080
```

### Lỗi: Container không start

```bash
# Xem logs để biết lỗi
docker-compose logs backend

# Kiểm tra container status
docker-compose ps

# Restart container
docker-compose restart backend
```

### Lỗi: MongoDB không kết nối được

```bash
# Kiểm tra MongoDB container
docker-compose ps mongodb

# Xem logs MongoDB
docker-compose logs mongodb

# Restart MongoDB
docker-compose restart mongodb

# Kiểm tra network
docker network ls
docker network inspect photomap-demo_geophoto-network
```

### Lỗi: Frontend không build được

```bash
# Xem logs build
docker-compose logs frontend

# Rebuild frontend
docker-compose build --no-cache frontend
docker-compose up -d frontend
```

### Lỗi: Backend không start

```bash
# Xem logs backend
docker-compose logs backend

# Rebuild backend
docker-compose build --no-cache backend
docker-compose up -d backend

# Kiểm tra MongoDB connection
docker-compose exec backend sh
# Trong container: ping mongodb
```

### Xóa tất cả và bắt đầu lại

```bash
# Dừng và xóa tất cả
docker-compose down -v

# Xóa images
docker-compose down --rmi all

# Xóa tất cả (cẩn thận!)
docker system prune -a --volumes

# Build lại từ đầu
docker-compose build --no-cache
docker-compose up -d
```

## 🔄 Workflow Development

### 1. Lần đầu setup

```bash
# Clone repository
git clone <repo>
cd PhotoMap-demo

# Chạy development mode
docker-compose -f docker-compose.dev.yml up -d

# Xem logs
docker-compose -f docker-compose.dev.yml logs -f
```

### 2. Code changes

```bash
# Code changes sẽ tự động reload
# Frontend: Vite HMR
# Backend: Có thể cần restart nếu thay đổi Java code
docker-compose -f docker-compose.dev.yml restart backend
```

### 3. Test production build

```bash
# Build production
docker-compose build

# Chạy production
docker-compose up -d

# Test tại http://localhost
```

## 📝 Environment Variables

Có thể override environment variables bằng file `.env`:

```bash
# Tạo file .env
cat > .env << EOF
JWT_SECRET=YourVeryLongSecretKeyHere
JWT_EXPIRATION=86400000
SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/geophoto
EOF

# Docker Compose sẽ tự động load .env
docker-compose up -d
```

Hoặc override trong `docker-compose.yml`:

```yaml
services:
  backend:
    environment:
      - JWT_SECRET=${JWT_SECRET:-default-secret}
```

## 🚢 Deploy Production

### Build và tag images

```bash
# Build images
docker-compose build

# Tag images (nếu push lên registry)
docker tag geophoto-backend:latest your-registry/geophoto-backend:v1.0.0
docker tag geophoto-frontend:latest your-registry/geophoto-frontend:v1.0.0

# Push lên registry
docker push your-registry/geophoto-backend:v1.0.0
docker push your-registry/geophoto-frontend:v1.0.0
```

### Production Recommendations

1. **Environment Variables:** Sử dụng secrets management
2. **SSL/TLS:** Thêm reverse proxy với SSL
3. **Backup:** Setup automated backup cho MongoDB
4. **Monitoring:** Thêm monitoring tools
5. **Logging:** Setup centralized logging

## 📚 Tài liệu thêm

- [Docker Documentation](https://docs.docker.com/)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [README.md](README.md) - Tổng quan dự án
- [QUICKSTART.md](QUICKSTART.md) - Quick start guide

## ❓ Câu hỏi thường gặp

### Q: Có cần cài Java, Node.js, MongoDB không?
**A:** Không! Docker sẽ tự động cài đặt mọi thứ trong containers.

### Q: Data có bị mất khi restart không?
**A:** Không, data được lưu trong Docker volumes và sẽ persist.

### Q: Có thể chạy nhiều instance không?
**A:** Có, nhưng cần thay đổi ports trong docker-compose.yml.

### Q: Làm sao để update code?
**A:** 
- Development: Code changes tự động reload
- Production: Rebuild images và restart containers

### Q: Làm sao để backup data?
**A:** Backup MongoDB volume và thư mục `backend/uploads`.

---

**Chúc bạn sử dụng thành công!** 🎉


