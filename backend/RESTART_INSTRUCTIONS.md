# Hướng Dẫn Restart Backend

## ⚠️ QUAN TRỌNG: Backend PHẢI được restart sau mỗi lần sửa code Java!

## Cách 1: Dùng Script (Khuyến nghị)

1. Mở terminal trong thư mục `backend`
2. Chạy:
   ```bash
   restart-backend.bat
   ```

## Cách 2: Restart Thủ Công

### Bước 1: Dừng Backend
```powershell
# Tìm process ID
netstat -ano | findstr :8080

# Dừng process (thay PID bằng số thực tế)
taskkill /PID <PID> /F
```

### Bước 2: Khởi động lại
```bash
cd backend
mvn clean spring-boot:run
```

## Cách 3: Dùng IDE

1. Dừng ứng dụng đang chạy trong IDE
2. Chạy lại `GeoPhotoApplication.java`

## Kiểm tra Backend đã chạy

Sau khi restart, bạn sẽ thấy trong console:
```
Started GeoPhotoApplication in X.XXX seconds
```

## Nếu vẫn gặp lỗi 403

1. Kiểm tra console của backend - xem có log "Skipping JWT validation" không
2. Kiểm tra xem MongoDB có đang chạy không: `netstat -ano | findstr :27017`
3. Kiểm tra console của trình duyệt (F12) - xem request/response chi tiết

