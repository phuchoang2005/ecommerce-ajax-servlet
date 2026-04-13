import mysql.connector

conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="root123",
)

cursor = conn.cursor()
cursor.execute("CREATE DATABASE IF NOT EXISTS ecommerce")
cursor.execute("use ecommerce")

sql = """
SET FOREIGN_KEY_CHECKS = 0;

-- ================================
-- USERS
-- ================================
CREATE TABLE USERS (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN','USER') NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;


-- ================================
-- PROFILES
-- ================================
CREATE TABLE PROFILES (
    profile_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    full_name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    address VARCHAR(255),

    CONSTRAINT fk_profiles_user
        FOREIGN KEY (user_id)
        REFERENCES USERS(user_id)
        ON DELETE CASCADE
) ENGINE=InnoDB;


-- ================================
-- CATEGORIES
-- ================================
CREATE TABLE CATEGORIES (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
) ENGINE=InnoDB;


-- ================================
-- PRODUCTS
-- ================================
CREATE TABLE PRODUCTS (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    unit VARCHAR(50),
    price DECIMAL(12,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    image_url VARCHAR(500),
    category_id INT NOT NULL,

    CONSTRAINT fk_products_category
        FOREIGN KEY (category_id)
        REFERENCES CATEGORIES(category_id)
        ON DELETE RESTRICT
) ENGINE=InnoDB;


-- ================================
-- CARTS
-- ================================
CREATE TABLE CARTS (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_carts_user
        FOREIGN KEY (user_id)
        REFERENCES USERS(user_id)
        ON DELETE CASCADE
) ENGINE=InnoDB;


-- ================================
-- CART ITEMS
-- ================================
CREATE TABLE CART_ITEMS (
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    added_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY(cart_id, product_id),

    CONSTRAINT fk_cartitems_cart
        FOREIGN KEY (cart_id)
        REFERENCES CARTS(cart_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_cartitems_product
        FOREIGN KEY (product_id)
        REFERENCES PRODUCTS(product_id)
        ON DELETE CASCADE
) ENGINE=InnoDB;


-- ================================
-- ORDERS
-- ================================
CREATE TABLE ORDERS (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(12,2) NOT NULL,
    status ENUM(
        'PENDING',
        'PAID',
        'SHIPPED',
        'COMPLETED',
        'CANCELLED'
    ) DEFAULT 'PENDING',
    payment_method VARCHAR(50),

    CONSTRAINT fk_orders_user
        FOREIGN KEY (user_id)
        REFERENCES USERS(user_id)
        ON DELETE CASCADE
) ENGINE=InnoDB;


-- ================================
-- ORDER DETAILS
-- ================================
CREATE TABLE ORDER_DETAILS (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(12,2) NOT NULL,

    PRIMARY KEY(order_id, product_id),

    CONSTRAINT fk_orderdetails_order
        FOREIGN KEY (order_id)
        REFERENCES ORDERS(order_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_orderdetails_product
        FOREIGN KEY (product_id)
        REFERENCES PRODUCTS(product_id)
        ON DELETE RESTRICT
) ENGINE=InnoDB;


-- ======================================
-- INDEXES FOR PRODUCT SEARCH
-- ======================================

-- Search by product name
CREATE INDEX idx_product_name
ON PRODUCTS(product_name);

-- Filter by category
CREATE INDEX idx_product_category
ON PRODUCTS(category_id);

-- Filter by price range
CREATE INDEX idx_product_price
ON PRODUCTS(price);

-- Filter by stock
CREATE INDEX idx_product_stock
ON PRODUCTS(stock);

-- Composite index for category + price filtering
CREATE INDEX idx_product_category_price
ON PRODUCTS(category_id, price);

-- FULLTEXT search (better than LIKE %keyword%)
CREATE FULLTEXT INDEX idx_product_fulltext
ON PRODUCTS(product_name);


SET FOREIGN_KEY_CHECKS = 1;
"""

# Thực thi lệnh tạo bảng
for statement in sql.split(";"):
    if statement.strip():
        cursor.execute(statement)

conn.commit()
cursor.close()
conn.close()

print("Database 'ecommerce' đã được cập nhật chuẩn OpenAPI & Phân quyền!")