Đây là nội dung file **`logging-standard.md`**. Việc thiết lập một quy chuẩn ghi nhật ký (logging) rõ ràng giúp bạn nhanh chóng phát hiện lỗi (troubleshooting), theo dõi hành vi người dùng và bảo vệ hệ thống khỏi các cuộc tấn công.

---

## NỘI DUNG FILE: `/docs/logging/logging-standard.md`

# QUY CHUẨN GHI NHẬT KÝ HỆ THỐNG (LOGGING STANDARDS)

## 1. Mục tiêu (Objectives)

* **Truy vết lỗi:** Xác định chính xác vị trí và nguyên nhân gây ra lỗi trong mã nguồn.
* **Giám sát bảo mật:** Theo dõi các hành vi bất thường (ví dụ: đăng nhập sai nhiều lần).
* **Phân tích hệ thống:** Đo lường hiệu suất và tần suất sử dụng các chức năng.

## 2. Công nghệ sử dụng (Tech Stack)

* **Thư viện giao diện:** SLF4J (Simple Logging Facade for Java).
* **Thư viện thực thi:** Logback hoặc Log4j2.
* **Tuyệt đối không sử dụng:** `System.out.println()` hoặc `e.printStackTrace()` vì chúng làm giảm hiệu năng và không thể quản lý tập trung.

## 3. Các cấp độ Log (Log Levels)

Hệ thống tuân thủ 5 cấp độ log chuẩn công nghiệp:

| Level | Khi nào sử dụng | Ví dụ |
| --- | --- | --- |
| **ERROR** | Khi có lỗi nghiêm trọng khiến chức năng không thể thực hiện. | Lỗi kết nối Database, Lỗi NullPointerException. |
| **WARN** | Các tình huống bất thường nhưng ứng dụng vẫn chạy được. | Đăng nhập sai quá 5 lần, Cảnh báo dung lượng bộ nhớ cao. |
| **INFO** | Ghi lại các sự kiện quan trọng trong luồng nghiệp vụ. | Người dùng "A" đăng nhập thành công, Khởi tạo Server. |
| **DEBUG** | Thông tin chi tiết dành cho lập trình viên khi phát triển. | Giá trị biến trung gian, Dữ liệu nhận về từ API. |
| **TRACE** | Thông tin chi tiết nhất (rất ít khi dùng trong sản xuất). | Từng bước chạy của các vòng lặp phức tạp. |

---

## 4. Định dạng Log (Log Format)

Mỗi dòng log phải bao gồm các thông tin sau để dễ dàng tìm kiếm:
`[%d{yyyy-MM-dd HH:mm:ss}] [%thread] [%-5level] %logger{36} - %msg%n`

* **Timestamp:** Thời gian chính xác xảy ra sự kiện.
* **Thread Name:** Luồng đang xử lý request (giúp phân biệt các request Ajax đồng thời).
* **Log Level:** Mức độ nghiêm trọng.
* **Logger Name:** Tên lớp (Class) đang ghi log.
* **Message:** Nội dung mô tả ngắn gọn, súc tích.

---

## 5. Nguyên tắc ghi Log (Best Practices)

### 5.1. Không ghi dữ liệu nhạy cảm

Tuyệt đối không được ghi các thông tin sau vào file log (tuân thủ bảo mật):

* Mật khẩu người dùng (Plaintext password).
* Mã số thẻ tín dụng, số căn cước.
* Token truy cập (Access Tokens).

### 5.2. Sử dụng Placeholders

Sử dụng cú pháp của SLF4J để tối ưu hiệu năng:

* **Nên dùng:** `log.info("User {} logged in from IP {}", username, ipAddress);`
* **Không nên:** `log.info("User " + username + " logged in");` (vì gây tốn bộ nhớ khi nối chuỗi vô ích).

### 5.3. Ghi Log ngoại lệ (Exception Logging)

Khi bắt lỗi trong khối `catch`, luôn ghi kèm theo đối tượng Exception để lấy đầy đủ StackTrace:

* **Chuẩn:** `log.error("Failed to connect to database", e);`

---

## 6. Cấu hình lưu trữ (Log Storage)

* **Môi trường Dev:** Log hiển thị ra Console để lập trình viên theo dõi.
* **Môi trường Production:** * Ghi vào file tại thư mục `/var/logs/app/`.
* **Log Rotation:** Mỗi file log tối đa 10MB. Hệ thống tự động nén và lưu trữ tối đa 30 ngày gần nhất (Daily Rolling).



---

## 7. Áp dụng cho chức năng Login

| Sự kiện | Cấp độ | Nội dung Log |
| --- | --- | --- |
| Bắt đầu xử lý Request | DEBUG | `Processing login request for user: {username}` |
| Sai mật khẩu | WARN | `Login failed for user: {username} - Reason: Invalid credentials` |
| Thành công | INFO | `User {username} authenticated successfully.` |
| Lỗi Database | ERROR | `Database error during authentication for user: {username}. StackTrace: ...` |

---

Tài liệu này sẽ giúp bạn và nhóm làm việc thống nhất về cách "giao tiếp" với hệ thống thông qua nhật ký.

**Tất cả các file tài liệu thiết kế đã sẵn sàng! Bây giờ, bạn có muốn tôi hướng dẫn refactor đoạn mã `UserLoginRepository` để sử dụng Logging và chuẩn bảo mật không?**
