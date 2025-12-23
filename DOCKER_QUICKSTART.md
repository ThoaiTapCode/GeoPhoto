# ⚡ Docker Quick Start - 3 bước

Hướng dẫn nhanh chạy GeoPhoto với Docker trong 3 bước!

## 🚀 Bước 1: Cài đặt Docker

**Windows/Mac:**
- Download: https://www.docker.com/products/docker-desktop
- Cài đặt và khởi động Docker Desktop

**Linux:**
```bash
sudo apt-get update
sudo apt-get install docker.io docker-compose
sudo systemctl start docker
```

## 🚀 Bước 2: Clone và chạy

```bash
# Clone repository
git clone <repository-url>
cd PhotoMap-demo

# Chạy ứng dụng (1 lệnh!)
docker-compose up -d
```

## 🚀 Bước 3: Mở trình duyệt

Truy cập: **http://localhost**

🎉 **Xong!** Ứng dụng đã chạy!

---

## 📝 Các lệnh hữu ích

```bash
# Xem logs
docker-compose logs -f

# Dừng ứng dụng
docker-compose down

# Restart
docker-compose restart

# Xem trạng thái
docker-compose ps
```

## 🔧 Development Mode (Hot Reload)

```bash
docker-compose -f docker-compose.dev.yml up -d
# Truy cập: http://localhost:5173
```

## ❓ Cần giúp đỡ?

Xem [DOCKER_GUIDE.md](DOCKER_GUIDE.md) để biết chi tiết!


