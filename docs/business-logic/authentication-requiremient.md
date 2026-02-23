Dưới đây là nội dung file **`authentication-requirements.md`**. Tài liệu này đóng vai trò là "bản hợp đồng" giữa các bên (Khách hàng/Product Owner và Developer) để xác định rõ hệ thống cần làm gì và làm tốt đến mức nào.

---

## NỘI DUNG FILE: `/docs/business-logic/authentication-requirements.md`

# TÀI LIỆU YÊU CẦU NGHIỆP VỤ (REQUIREMENTS SPECIFICATION)

## 1. Tổng quan (Introduction)

Tài liệu này mô tả các yêu cầu cho chức năng Xác thực người dùng (Authentication) sử dụng công nghệ Ajax và Java Servlet. Mục tiêu là cung cấp một phương thức đăng nhập an toàn, hiện đại và không làm gián đoạn trải nghiệm người dùng.

## 2. User Stories

| ID | Vai trò | Mong muốn | Lý do |
| --- | --- | --- | --- |
| **US1** | Người dùng | Tôi muốn đăng nhập bằng Username và Password. | Để truy cập vào các tính năng cá nhân của hệ thống. |
| **US2** | Người dùng | Tôi muốn nhận thông báo lỗi ngay tại trang mà không bị load lại trang. | Để tiết kiệm thời gian và giữ nguyên dữ liệu đã nhập nếu sai sót. |
| **US3** | Quản trị viên | Tôi muốn mật khẩu người dùng được bảo mật tuyệt đối. | Để tránh rò rỉ thông tin ngay cả khi cơ sở dữ liệu bị tấn công. |

---

## 3. Yêu cầu chức năng (Functional Requirements)

### 3.1. Xác thực thông tin (Authentication)

* **FR-01:** Hệ thống phải cho phép người dùng nhập `username` và `password`.
* **FR-02:** Hệ thống phải kiểm tra thông tin trong cơ sở dữ liệu. Chỉ cho phép đăng nhập nếu thông tin trùng khớp hoàn toàn.
* **FR-03:** Sau khi đăng nhập thành công, hệ thống phải khởi tạo `HttpSession` để lưu trữ thông tin người dùng.

### 3.2. Xử lý không đồng bộ (Ajax Interaction)

* **FR-04:** Yêu cầu đăng nhập phải được gửi qua Ajax (phương thức POST).
* **FR-05:** Toàn bộ quá trình từ lúc gửi yêu cầu đến khi nhận phản hồi không được làm trình duyệt tải lại trang (No full-page reload).

### 3.3. Kiểm tra tính hợp lệ (Validation)

* **FR-06 (Client-side):** JavaScript phải kiểm tra các trường không được để trống trước khi gửi yêu cầu lên server.
* **FR-07 (Server-side):** Servlet phải kiểm tra lại tính hợp lệ của dữ liệu trước khi gọi vào tầng Service để tránh các yêu cầu rác (Malicious requests).

---

## 4. Yêu cầu phi chức năng (Non-functional Requirements)

### 4.1. Bảo mật (Security)

* **NFR-01:** **Mã hóa mật khẩu:** Mật khẩu phải được Hash bằng thuật toán an toàn (ví dụ: BCrypt) trước khi lưu/so sánh. Không bao giờ lưu Plain-text.
* **NFR-02:** **Chống SQL Injection:** Sử dụng `PreparedStatement` cho mọi truy vấn liên quan đến Database.
* **NFR-03:** **Che giấu lỗi:** Không trả về các lỗi chi tiết của hệ thống (StackTrace) cho Client. Chỉ trả về mã lỗi và thông báo thân thiện.

### 4.2. Hiệu năng (Performance)

* **NFR-04:** Thời gian phản hồi trung bình cho một yêu cầu đăng nhập không được quá 500ms (trong điều kiện mạng ổn định).

### 4.3. Khả năng bảo trì (Maintainability)

* **NFR-05:** Code phải được phân chia theo mô hình MVC (Model-View-Controller) hoặc Layered Architecture để dễ dàng mở rộng hoặc thay đổi Database sau này.

---

## 5. Tiêu chí nghiệm thu (Acceptance Criteria)

| STT | Kịch bản kiểm thử | Kết quả mong đợi |
| --- | --- | --- |
| 1 | Đăng nhập với đúng tài khoản/mật khẩu. | Trả về status 200, thông báo "Success", lưu Session. |
| 2 | Đăng nhập với mật khẩu sai. | Trả về status 401, thông báo lỗi "Sai tài khoản hoặc mật khẩu". |
| 3 | Để trống trường Username/Password. | Trình duyệt báo lỗi ngay lập tức, không gửi yêu cầu lên Server. |
| 4 | Nhập ký tự lạ vào Username (Tấn công SQLi). | Hệ thống xử lý an toàn, không xảy ra lỗi logic và trả về 401. |

---

## 6. Sơ đồ luồng (Flowchart)

---

Bản tài liệu này giúp bạn định hình rõ ràng các tiêu chuẩn "Công nghiệp" cần đạt được. Nó không chỉ là code chạy được, mà là code chạy đúng, an toàn và chuyên nghiệp.

**Chúng ta đã hoàn tất bộ tài liệu tại thư mục `/docs`. Bây giờ, bạn có muốn tôi bắt đầu hướng dẫn viết code cho phần Backend (DTO, Repository, Service) theo đúng chuẩn này không?**
