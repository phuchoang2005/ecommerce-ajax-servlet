# 📚 PROJECT DOCUMENTATION ROADMAP

Chào mừng bạn đến với kho tài liệu kỹ thuật của dự án **Login Module (Ajax + Servlet)**. Tài liệu này được xây dựng theo chuẩn công nghiệp nhằm đảm bảo tính minh bạch, bảo mật và khả năng mở rộng của hệ thống.

---

## 📂 CẤU TRÚC THƯ MỤC TÀI LIỆU

```text
/docs
├── /business-logic         # Định nghĩa "Tại sao" và "Cái gì"
│   └── authentication-requirements.md  <-- Bắt đầu từ đây
│
├── /architecture           # Định nghĩa "Như thế nào" (Tầm nhìn hệ thống)
│   └── login-sequence-diagram.mermaid
│
├── /api                    # Quy chuẩn giao tiếp giữa Front-end và Back-end
│   └── auth-api.yaml       <-- Đặc tả Swagger/OpenAPI
│
├── /database               # Cấu trúc lưu trữ dữ liệu
│   └── database-design.md
│
└── /logging                # Quy chuẩn giám sát và vận hành
    └── logging-standard.md

```

---

## 🛠 CÔNG NGHỆ ÁP DỤNG (TECH STACK)

* **Back-end:** Java Servlet, JDBC.
* **Front-end:** HTML/CSS, JavaScript (Fetch API/Ajax).
* **Database:** MySQL.
* **Data Format:** JSON (Standardized).
* **Documentation:** Markdown, OpenAPI 3.0, Mermaid.js.

---

## 🧭 LỘ TRÌNH ĐỌC TÀI LIỆU (RECOMMENDED READING PATH)

Để hiểu trọn vẹn dự án, chúng tôi đề xuất bạn đọc tài liệu theo thứ tự sau:

1. **[Authentication Requirements](./business-logic/authentication-requirement.md):** Hiểu về yêu cầu nghiệp vụ, User Stories và các tiêu chí nghiệm thu.
2. **[Database Design](./database/database-design.md):** Xem cấu trúc các bảng và cách dữ liệu được liên kết để phục vụ logic đăng nhập.
3. **[Sequence Diagram](./architecture/login-sequence-diagram.mermaid):** Cái nhìn trực quan về luồng đi của dữ liệu từ Browser qua các lớp (Layer) xử lý trong Java.
4. **[API Specification](api/auth-api.yaml):** Chi tiết về các Endpoint, cấu trúc JSON Request/Response và các mã lỗi HTTP.
5. **[Logging Standard](./logging/logging-standard.md):** Cách hệ thống ghi lại lịch sử hoạt động để bảo trì và xử lý sự cố.

---

## 💡 CÁCH CẬP NHẬT TÀI LIỆU

* **Sơ đồ:** Sử dụng [Mermaid Live Editor](https://www.google.com/search?q=https://mermaid.live/) để chỉnh sửa các file `.mermaid`.
* **API:** Sử dụng [Swagger Editor](https://www.google.com/search?q=https://editor.swagger.io/) để cập nhật file `auth-api.yaml`.
* **Markdown:** Mọi tệp văn bản đều tuân thủ định dạng Markdown chuẩn.

---

> **Lưu ý:** Đây là tài liệu sống (Living Document). Mọi thay đổi về code làm ảnh hưởng đến logic hệ thống đều phải được cập nhật tương ứng tại đây.

---