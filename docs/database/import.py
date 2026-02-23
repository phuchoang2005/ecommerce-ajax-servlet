import mysql.connector

# Kết nối DB
conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="root123",
    database="ecommerce",
    allow_local_infile=True
)

cursor = conn.cursor()

# Đọc file SQL
with open("dummy_data.sql", "r", encoding="utf-8") as f:
    sql_script = f.read()

# Tách từng câu lệnh
statements = sql_script.split(";")

for stmt in statements:
    stmt = stmt.strip()
    if stmt:
        cursor.execute(stmt)

conn.commit()
cursor.close()
conn.close()

print("✅ Dummy data imported successfully!")
