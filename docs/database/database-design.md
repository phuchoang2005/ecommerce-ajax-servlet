Đây là nội dung file **`database-design.md`** được trình bày theo chuẩn tài liệu kỹ thuật chuyên nghiệp. File này cung cấp cái nhìn tổng quan về cấu trúc dữ liệu, các mối quan hệ (ERD) và các ràng buộc nghiệp vụ quan trọng.

---

## NỘI DUNG FILE: `/docs/database/database-design.md`

# THIẾT KẾ CƠ SỞ DỮ LIỆU (DATABASE DESIGN)

## 1. Sơ đồ quan hệ thực thể (ER Diagram)

Sơ đồ dưới đây mô tả mối quan hệ giữa các bảng trong hệ thống quản lý bán hàng:

``` mermaid

erDiagram
USERS ||--|| PROFILES : "has"
USERS ||--o{ ORDERS : "places"
CATEGORIES ||--o{ PRODUCTS : "contains"
ORDERS ||--|{ ORDER_DETAILS : "includes"
PRODUCTS ||--o{ ORDER_DETAILS : "ordered_in"

    USERS {
        int user_id PK
        string username "unique"
        string password
        enum role "ADMIN, USER"
        timestamp created_at
    }

    PROFILES {
        int profile_id PK
        int user_id FK "unique"
        string full_name
        string phone
        string email
        string address
    }

    CATEGORIES {
        int category_id PK
        string category_name
    }

    PRODUCTS {
        int product_id PK
        string product_name
        string unit
        decimal price
        int stock
        string image_url
        int category_id FK
    }

    ORDERS {
        int order_id PK
        int user_id FK
        datetime order_date
        decimal total_amount
        enum status "PENDING, PAID, etc."
        string payment_method
    }

    ORDER_DETAILS {
        int order_id PK, FK
        int product_id PK, FK
        int quantity
        decimal unit_price
    }

```
---

## 2. Chi tiết các bảng (Data Dictionary) - Phiên bản chuẩn OpenAPI

### 2.1. Bảng `users` (Tài khoản & Phân quyền)

Đây là "nguồn sự thật" cho quá trình xác thực. Việc dùng `user_id` (INT) thay vì `username` làm PK giúp tối ưu hiệu năng và khớp với đặc tả `userId` trong thiết kế API.

| Column | Data Type | Constraints | Description |
| --- | --- | --- | --- |
| **user_id** | INT | **PK**, AUTO_INCREMENT | ID định danh duy nhất (Trả về trong LoginResponse). |
| **username** | VARCHAR(50) | UNIQUE, NOT NULL | Tên đăng nhập (Dùng để login). |
| **password** | VARCHAR(255) | NOT NULL | Mật khẩu đã Hash (BCrypt/Argon2). |
| **role** | ENUM('ADMIN','USER') | DEFAULT 'USER' | Phân quyền hệ thống. |
| **created_at** | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Thời điểm tạo tài khoản. |

### 2.2. Bảng `profiles` (Thông tin cá nhân)

Tách biệt thông tin đăng nhập và thông tin cá nhân giúp hệ thống linh hoạt hơn (ví dụ: một User có thể có nhiều địa chỉ hoặc một Admin không nhất thiết phải là khách hàng mua sắm).

| Column | Data Type | Constraints | Description |
| --- | --- | --- | --- |
| **profile_id** | INT | **PK**, AUTO_INCREMENT | ID định danh hồ sơ. |
| **user_id** | INT | **FK**, UNIQUE | Liên kết 1:1 với bảng `users`. |
| **full_name** | VARCHAR(100) | NOT NULL | Họ và tên hiển thị trên UI. |
| **phone** | VARCHAR(20) |  | Số điện thoại liên lạc. |
| **email** | VARCHAR(100) |  | Email nhận thông báo đơn hàng. |
| **address** | VARCHAR(255) |  | Địa chỉ giao hàng mặc định. |

### 2.3. Bảng `categories` và `products` (Danh mục & Sản phẩm)

Chuyển đổi sang tên tiếng Anh để đồng bộ với các thư viện Java (như Hibernate/JPA) và dễ dàng mapping sang JSON Object trong API.

**Bảng `categories` (Danh mục):**

* `category_id`: Mã loại sản phẩm (PK).
* `category_name`: Tên danh mục (Điện tử, Thời trang...).

**Bảng `products` (Sản phẩm):**

* `price`: Dùng `DECIMAL(12,2)` để đảm bảo tính chính xác cho các giao dịch tiền tệ lớn.
* `stock`: Quản lý số lượng tồn kho (quan trọng cho logic "Hết hàng" trên Web).
* `category_id`: Khóa ngoại liên kết tới `categories`.

### 2.4. Bảng `orders` và `order_details` (Giao dịch)

**Bảng `orders` (Hóa đơn):**

* `status`: Sử dụng `ENUM('PENDING', 'PAID', 'SHIPPING', 'DELIVERED', 'CANCELLED')`. Việc này giúp Frontend hiển thị các Label trạng thái khác nhau cho người dùng.
* `total_amount`: Lưu tổng tiền cuối cùng sau khi tính toán (để truy vấn báo cáo nhanh hơn).

**Bảng `order_details` (Chi tiết hóa đơn):**

* **Composite Primary Key:** Khóa chính kết hợp `(order_id, product_id)`.
* `unit_price`: Lưu giá tại thời điểm mua. Đây là quy tắc bắt buộc trong E-commerce để bảo toàn dữ liệu lịch sử nếu sau này sản phẩm tăng/giảm giá.

---

## 3. Các ràng buộc toàn vẹn & Logic nghiệp vụ

### 3.1. Quy tắc Xóa/Cập nhật (Referential Integrity)

* **ON DELETE SET NULL (`products`):** Nếu một Danh mục bị xóa, các Sản phẩm thuộc danh mục đó sẽ không bị xóa mà chuyển `category_id` về `NULL` (Tránh mất dữ liệu sản phẩm).
* **ON DELETE CASCADE (`profiles` & `order_details`):** Nếu một `users` hoặc `orders` bị xóa, các thông tin chi tiết liên quan sẽ tự động bị xóa theo để tránh dữ liệu "rác".
* **ON DELETE RESTRICT (`users` trong `orders`):** Không cho phép xóa một User nếu người đó đã có lịch sử đơn hàng (Để giữ lại dữ liệu kế toán/báo cáo).

### 3.2. Kiểm soát Phân quyền (Role-based Access Control)

Dựa trên cột `role` trong bảng `users`:

* **Logic Java Servlet:** Khi nhận phản hồi từ Database, server sẽ kiểm tra giá trị `role`.
* Nếu `role = 'ADMIN'`: Cho phép truy cập `/admin/*` (Quản lý sản phẩm, Xem doanh thu).
* Nếu `role = 'USER'`: Chỉ cho phép truy cập `/account/*` và `/checkout`.


---

## 4. Ưu điểm của thiết kế

1. **Chuẩn hóa Quốc tế:** Việc sử dụng tên bảng/cột tiếng Anh giúp bạn dễ dàng làm việc với các Frontend Framework (React/Angular/Vue) và các thư viện Map dữ liệu.
2. **Khớp 100% với OpenAPI:** Bạn có thể sinh code DTO trong Java tự động mà không cần sửa tên trường (`userId` khớp `user_id`, `role` khớp `role`).
3. **Khả năng mở rộng:** Thiết kế này cho phép bạn thêm các tính năng như "Sản phẩm yêu thích", "Đánh giá 5 sao" hoặc "Mã giảm giá" bằng cách thêm các bảng mới liên kết với `product_id` và `user_id` một cách dễ dàng.
