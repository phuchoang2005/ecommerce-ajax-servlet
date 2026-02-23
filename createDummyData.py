import random
from faker import Faker

fake = Faker("vi_VN")

NUM_USERS = 5
NUM_CUSTOMERS = 20
NUM_CATEGORIES = 6
NUM_PRODUCTS = 50
NUM_ORDERS = 40

sql = []

# ---------- USERS ----------
sql.append("-- USERS")
for i in range(NUM_USERS):
    username = f"user{i+1}"
    password = "123456"
    sql.append(
        f"INSERT INTO users(username, password) VALUES ('{username}', '{password}');"
    )

# ---------- LOAISP ----------
categories = [
    "Điện thoại",
    "Laptop",
    "Phụ kiện",
    "Gia dụng",
    "Thời trang",
    "Sách"
]

sql.append("\n-- LOAISP")
for i, name in enumerate(categories, start=1):
    sql.append(
        f"INSERT INTO loaisp(maloai, tenloai) VALUES ({i}, '{name}');"
    )

# ---------- KHACHHANG ----------
sql.append("\n-- KHACHHANG")
for i in range(NUM_CUSTOMERS):
    name = fake.name()
    phone = fake.phone_number()
    email = fake.email()
    address = fake.address().replace("\n", " ")
    sql.append(
        f"""INSERT INTO khachhang(hoten, sdt, email, diachi)
            VALUES ('{name}', '{phone}', '{email}', '{address}');"""
    )

# ---------- SANPHAM ----------
sql.append("\n-- SANPHAM")
for i in range(NUM_PRODUCTS):
    name = fake.word().capitalize() + " " + fake.word()
    unit = "cái"
    origin = random.choice(["Việt Nam", "Trung Quốc", "Mỹ", "Nhật", "Hàn"])
    price = random.randint(50, 2000) * 1000
    image = f"sp{i+1}.jpg"
    maloai = random.randint(1, NUM_CATEGORIES)

    sql.append(
        f"""INSERT INTO sanpham(tensp, donvitinh, nuocsx, gia, hinhanh, maloai)
            VALUES ('{name}', '{unit}', '{origin}', {price}, '{image}', {maloai});"""
    )

# ---------- HOADON + CTHD ----------
sql.append("\n-- HOADON & CTHD")

for i in range(NUM_ORDERS):
    makh = random.randint(1, NUM_CUSTOMERS)
    pay = random.choice(["COD", "BANKING", "MOMO", "VNPAY"])

    sql.append(
        f"INSERT INTO hoadon(makh, thanhtoan) VALUES ({makh}, '{pay}');"
    )

    sohd = i + 1
    num_items = random.randint(1, 5)
    products = random.sample(range(1, NUM_PRODUCTS + 1), num_items)

    for masp in products:
        qty = random.randint(1, 3)
        price = random.randint(50, 2000) * 1000
        sql.append(
            f"""INSERT INTO cthd(sohd, masp, soluong, donggia)
                VALUES ({sohd}, {masp}, {qty}, {price});"""
        )

# ---------- EXPORT ----------
with open("dummy_data.sql", "w", encoding="utf-8") as f:
    f.write("\n".join(sql))

print("Generated dummy_data.sql successfully.")
