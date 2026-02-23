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

CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS khachhang (
    makh INT AUTO_INCREMENT PRIMARY KEY,
    hoten VARCHAR(100) NOT NULL,
    sdt VARCHAR(20),
    email VARCHAR(100),
    diachi VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS loaisp (
    maloai INT AUTO_INCREMENT PRIMARY KEY,
    tenloai VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS sanpham (
    masp INT AUTO_INCREMENT PRIMARY KEY,
    tensp VARCHAR(150) NOT NULL,
    donvitinh VARCHAR(50),
    nuocsx VARCHAR(100),
    gia DECIMAL(12,2) NOT NULL,
    hinhanh VARCHAR(255),
    maloai INT,
    CONSTRAINT fk_sanpham_loaisp
        FOREIGN KEY (maloai)
        REFERENCES loaisp(maloai)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS hoadon (
    sohd INT AUTO_INCREMENT PRIMARY KEY,
    ngayhoadon DATETIME DEFAULT CURRENT_TIMESTAMP,
    makh INT,
    thanhtoan VARCHAR(50),
    CONSTRAINT fk_hoadon_khachhang
        FOREIGN KEY (makh)
        REFERENCES khachhang(makh)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS cthd (
    sohd INT,
    masp INT,
    soluong INT NOT NULL,
    donggia DECIMAL(12,2) NOT NULL,
    PRIMARY KEY (sohd, masp),
    CONSTRAINT fk_cthd_hoadon
        FOREIGN KEY (sohd)
        REFERENCES hoadon(sohd)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_cthd_sanpham
        FOREIGN KEY (masp)
        REFERENCES sanpham(masp)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

"""

for statement in sql.split(";"):
    if statement.strip():
        cursor.execute(statement)

conn.commit()
cursor.close()
conn.close()

print("Created all tables successfully.")
