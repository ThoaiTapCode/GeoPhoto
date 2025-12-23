# 🤝 Hướng dẫn đóng góp

Cảm ơn bạn đã quan tâm đến việc đóng góp cho GeoPhoto!

## 📋 Quy trình đóng góp

1. **Fork repository**
   ```bash
   # Click nút Fork trên GitHub
   ```

2. **Clone repository của bạn**
   ```bash
   git clone https://github.com/YOUR_USERNAME/PhotoMap-demo.git
   cd PhotoMap-demo
   ```

3. **Tạo branch mới**
   ```bash
   git checkout -b feature/your-feature-name
   # hoặc
   git checkout -b fix/your-bug-fix
   ```

4. **Thực hiện thay đổi**
   - Viết code
   - Thêm comments nếu cần
   - Đảm bảo code không có lỗi

5. **Commit changes**
   ```bash
   git add .
   git commit -m "Add: Mô tả ngắn gọn về thay đổi"
   ```

6. **Push lên GitHub**
   ```bash
   git push origin feature/your-feature-name
   ```

7. **Tạo Pull Request**
   - Vào GitHub repository của bạn
   - Click "New Pull Request"
   - Mô tả chi tiết về thay đổi

## 📝 Quy tắc code

### Backend (Java)
- Tuân thủ Java naming conventions
- Sử dụng Lombok để giảm boilerplate
- Thêm JavaDoc cho các method public
- Xử lý exceptions đúng cách

### Frontend (React/JavaScript)
- Sử dụng functional components
- Sử dụng hooks thay vì class components
- Tên component phải viết hoa chữ cái đầu
- Thêm comments cho logic phức tạp

### Commit messages
- Format: `Type: Mô tả ngắn gọn`
- Types: `Add`, `Fix`, `Update`, `Remove`, `Refactor`, `Docs`
- Ví dụ: `Add: Feature upload multiple photos`

## 🧪 Testing

Trước khi submit PR, đảm bảo:
- [ ] Code compile không có lỗi
- [ ] Ứng dụng chạy được
- [ ] Không có lỗi console
- [ ] Test các tính năng liên quan

## 📚 Tài liệu

Nếu thêm tính năng mới, vui lòng:
- Cập nhật README.md nếu cần
- Thêm comments trong code
- Cập nhật API documentation nếu có

## ❓ Câu hỏi?

Nếu có câu hỏi, vui lòng:
- Mở Issue trên GitHub
- Hoặc liên hệ maintainer

Cảm ơn bạn đã đóng góp! 🎉

