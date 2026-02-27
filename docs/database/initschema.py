import mysql.connector

conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="root123",
)

cursor = conn.cursor()
cursor.execute("CREATE DATABASE IF NOT EXISTS ecommerce_new")
cursor.execute("use ecommerce_new")

sql = """
-- 1. Bảng Users: Chứa thông tin đăng nhập và phân quyền
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER') DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 2. Bảng Profiles (Khách hàng): Chứa thông tin cá nhân, liên kết với users
CREATE TABLE IF NOT EXISTS profiles (
    profile_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    address VARCHAR(255),
    CONSTRAINT fk_profile_user
        FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
);

-- 3. Bảng Categories (Loại sản phẩm)
CREATE TABLE IF NOT EXISTS categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);

-- 4. Bảng Products (Sản phẩm)
CREATE TABLE IF NOT EXISTS products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(150) NOT NULL,
    unit VARCHAR(50),
    price DECIMAL(12,2) NOT NULL,
    stock INT DEFAULT 0,
    image_url VARCHAR(255),
    category_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id) REFERENCES categories(category_id)
        ON UPDATE CASCADE ON DELETE SET NULL
);

-- 5. Bảng Orders (Hóa đơn)
CREATE TABLE IF NOT EXISTS orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(12,2),
    status ENUM('PENDING', 'PAID', 'SHIPPING', 'DELIVERED', 'CANCELLED') DEFAULT 'PENDING',
    payment_method VARCHAR(50),
    CONSTRAINT fk_order_user
        FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 6. Bảng OrderDetails (Chi tiết hóa đơn)
CREATE TABLE IF NOT EXISTS order_details (
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    unit_price DECIMAL(12,2) NOT NULL,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_detail_order
        FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    CONSTRAINT fk_detail_product
        FOREIGN KEY (product_id) REFERENCES products(product_id)
);
"""

# Thực thi lệnh tạo bảng
for statement in sql.split(";"):
    if statement.strip():
        cursor.execute(statement)

# Chèn dữ liệu mẫu để test API
seed_data = [
    "INSERT IGNORE INTO users (username, password, role) VALUES ('admin_user', 'P@ssw0rd123', 'ADMIN')",
    "INSERT IGNORE INTO users (username, password, role) VALUES ('customer01', 'password123', 'USER')"
]
for data in seed_data:
    cursor.execute(data)

conn.commit()
cursor.close()
conn.close()

print("Database 'ecommerce' đã được cập nhật chuẩn OpenAPI & Phân quyền!")