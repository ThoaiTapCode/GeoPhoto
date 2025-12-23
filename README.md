# 📸 GeoPhoto - Personal Photo Map Manager

Ứng dụng quản lý ảnh cá nhân với bản đồ GPS - Upload ảnh, tự động trích xuất GPS từ EXIF metadata, và hiển thị trên bản đồ tương tác!

## ✨ Tính năng

- 🗺️ **Bản đồ tương tác** - Hiển thị ảnh trên bản đồ OpenStreetMap với Leaflet
- 📤 **Upload ảnh** - Tự động trích xuất GPS từ EXIF metadata
- 📍 **Thêm vị trí thủ công** - Thêm GPS cho ảnh không có metadata
- 🔍 **Tìm kiếm địa điểm** - Geocoding và reverse geocoding
- 🔐 **Xác thực JWT** - Đăng ký/đăng nhập an toàn với Spring Security
- 🎨 **UI hiện đại** - Responsive design với Tailwind CSS
- 📱 **Marker clustering** - Nhóm ảnh khi zoom out

## 🚀 Tech Stack

**Backend:**
- Spring Boot 3.2.0
- MongoDB
- Spring Security + JWT
- Metadata Extractor (GPS/EXIF extraction)

**Frontend:**
- React 18
- Vite
- Tailwind CSS
- React-Leaflet + Leaflet
- Axios

**Database:**
- MongoDB 7.0+

## 📋 Yêu cầu hệ thống

### Với Docker (Khuyến nghị)
- **Docker** 20.10+
- **Docker Compose** 2.0+

### Không dùng Docker
- **Java 17+** (JDK)
- **Maven 3.6+**
- **Node.js 18+** và **npm**
- **MongoDB 7.0+**

### Kiểm tra cài đặt

```bash
# Kiểm tra Java
java -version

# Kiểm tra Maven
mvn --version

# Kiểm tra Node.js và npm
node --version
npm --version

# Kiểm tra MongoDB
mongod --version
```

## 🛠️ Hướng dẫn cài đặt và chạy

### 🐳 Cách 1: Chạy với Docker (Khuyến nghị - Dễ nhất!)

**Yêu cầu:** Chỉ cần Docker và Docker Compose

```bash
# Clone repository
git clone <repository-url>
cd PhotoMap-demo

# Chạy tất cả services (MongoDB + Backend + Frontend)
docker-compose up -d

# Xem logs
docker-compose logs -f

# Dừng tất cả services
docker-compose down
```

**Truy cập ứng dụng:**
- Frontend: http://localhost (port 80)
- Backend API: http://localhost:8080
- MongoDB: localhost:27017

**Development mode với hot reload:**
```bash
docker-compose -f docker-compose.dev.yml up -d
# Frontend: http://localhost:5173
```

**Xem thêm:** 
- [DOCKER_GUIDE.md](DOCKER_GUIDE.md) - Hướng dẫn chi tiết Docker
- [DOCKER.md](DOCKER.md) - Tài liệu kỹ thuật Docker

---

### 💻 Cách 2: Chạy thủ công (Local Development)

### Bước 1: Clone repository

```bash
git clone <repository-url>
cd PhotoMap-demo
```

### Bước 2: Khởi động MongoDB

**Windows:**
```bash
# Nếu MongoDB đã được cài đặt như service, nó sẽ tự động chạy
# Hoặc chạy thủ công:
mongod --dbpath=C:\data\db
```

**Linux/Mac:**
```bash
# Nếu MongoDB đã được cài đặt như service
sudo systemctl start mongod

# Hoặc chạy thủ công:
mongod --dbpath=./data/db
```

**Docker (tùy chọn):**
```bash
docker run -d -p 27017:27017 --name mongodb mongo:7
```

### Bước 3: Cấu hình Backend

1. Kiểm tra file `backend/src/main/resources/application.properties`:
   - MongoDB URI: `mongodb://localhost:27017/geophoto`
   - Server port: `8080`
   - Upload directory: `uploads`

2. Tạo thư mục uploads (nếu chưa có):
```bash
cd backend
mkdir -p uploads
```

### Bước 4: Chạy Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Backend sẽ chạy tại: **http://localhost:8080**

### Bước 5: Cài đặt và chạy Frontend

Mở terminal mới:

```bash
cd frontend
npm install
npm run dev
```

Frontend sẽ chạy tại: **http://localhost:5173**

## 🎯 Sử dụng ứng dụng

1. Mở trình duyệt và truy cập: **http://localhost:5173**
2. **Đăng ký** tài khoản mới hoặc **đăng nhập** nếu đã có tài khoản
3. **Upload ảnh** - Ảnh có GPS sẽ tự động hiển thị trên bản đồ
4. **Thêm vị trí** - Click vào ảnh không có GPS để thêm vị trí thủ công
5. **Xem chi tiết** - Click vào marker trên bản đồ để xem thông tin ảnh

## 📁 Cấu trúc dự án

```
PhotoMap-demo/
├── backend/                 # Spring Boot Backend
│   ├── src/
│   │   └── main/
│   │       ├── java/        # Java source code
│   │       └── resources/
│   │           └── application.properties
│   ├── uploads/            # Thư mục lưu ảnh upload
│   └── pom.xml
├── frontend/               # React Frontend
│   ├── src/
│   │   ├── components/     # React components
│   │   ├── services/       # API services
│   │   └── context/        # React context
│   ├── package.json
│   └── vite.config.js
└── README.md
```

## 🔧 Cấu hình

### Backend Configuration

File: `backend/src/main/resources/application.properties`

```properties
# Server
server.port=8080

# MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/geophoto
spring.data.mongodb.database=geophoto

# File Upload
app.upload.dir=uploads
spring.servlet.multipart.max-file-size=10MB

# JWT
jwt.secret=MyVerySecretKeyForGeoPhotoApplicationThatIsLongEnoughForHS512Algorithm
jwt.expiration=86400000
```

### Frontend Configuration

File: `frontend/src/services/authService.js` và `photoService.js`

- API URL: `http://localhost:8080/api`

## 🐛 Troubleshooting

### Lỗi kết nối MongoDB

```
Error: Cannot connect to MongoDB
```

**Giải pháp:**
- Đảm bảo MongoDB đang chạy: `mongod --version`
- Kiểm tra port 27017 có bị chiếm không
- Kiểm tra MongoDB URI trong `application.properties`

### Lỗi CORS

```
Access-Control-Allow-Origin error
```

**Giải pháp:**
- Đảm bảo backend đang chạy trên port 8080
- Kiểm tra CORS configuration trong `SecurityConfig.java`

### Lỗi upload ảnh

```
Failed to upload file
```

**Giải pháp:**
- Kiểm tra thư mục `backend/uploads` có tồn tại không
- Kiểm tra quyền ghi file
- Kiểm tra kích thước file (max 10MB)

### Port đã được sử dụng

```
Port 8080 is already in use
```

**Giải pháp:**
- Thay đổi port trong `application.properties`: `server.port=8081`
- Hoặc dừng process đang sử dụng port đó

## 📝 API Endpoints

### Authentication
- `POST /api/auth/register` - Đăng ký
- `POST /api/auth/login` - Đăng nhập
- `GET /api/auth/me` - Lấy thông tin user hiện tại

### Photos
- `GET /api/photos` - Lấy tất cả ảnh
- `GET /api/photos/with-gps` - Lấy ảnh có GPS
- `GET /api/photos/{id}` - Lấy ảnh theo ID
- `POST /api/photos/upload` - Upload ảnh
- `PUT /api/photos/{id}/location` - Cập nhật vị trí
- `DELETE /api/photos/{id}` - Xóa ảnh

## 🧪 Testing

### Test Data (Development)

```bash
# Thêm dữ liệu mẫu
curl -X POST http://localhost:8080/api/test/add-sample-photos

# Xóa tất cả ảnh
curl -X DELETE http://localhost:8080/api/test/clear-all-photos
```

## 📦 Build cho Production

### Backend

```bash
cd backend
mvn clean package
java -jar target/geophoto-backend-1.0.0.jar
```

### Frontend

```bash
cd frontend
npm run build
# Files sẽ được build vào thư mục dist/
```

## 🤝 Đóng góp

1. Fork repository
2. Tạo feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Mở Pull Request

## 📄 License

MIT License

## 👨‍💻 Tác giả

GeoPhoto Team

## 🙏 Lời cảm ơn

- OpenStreetMap cho bản đồ
- Leaflet cho map library
- Spring Boot community
- React community

---

**Lưu ý:** Đảm bảo MongoDB đang chạy trước khi khởi động backend!
