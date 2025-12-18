# 📸 GeoPhoto - Personal Photo Map Manager

Ứng dụng quản lý ảnh cá nhân với bản đồ GPS - Upload ảnh, tự động trích xuất GPS, và hiển thị trên bản đồ tương tác!

## ✨ Tính năng

- 🗺️ Hiển thị ảnh trên bản đồ tương tác (OpenStreetMap + Leaflet)
- 📤 Upload ảnh với tự động trích xuất GPS từ EXIF
- 📍 Thêm vị trí cho ảnh không có GPS
- 🔍 Tìm kiếm địa điểm và reverse geocoding
- 🔐 Xác thực JWT với Spring Security
- 🎨 UI hiện đại responsive với Tailwind CSS

## 🚀 Tech Stack

**Backend:** Spring Boot 3.2.0 + MongoDB + JWT  
**Frontend:** React 18 + Vite + Tailwind CSS + React-Leaflet  
**Database:** MongoDB 7.0+

## 🛠️ Cài đặt

### Backend
```bash
cd backend
mvn spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm run dev
```

### MongoDB
```bash
mongod --dbpath=./data/db
```

## 📖 Chi tiết

- Server Backend: http://localhost:8080
- Server Frontend: http://localhost:5173
- Database: MongoDB tại localhost:27017

## 📝 License

MIT License
