import mysql.connector

conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="root123"
)

cursor = conn.cursor()

cursor.execute("DROP DATABASE IF EXISTS ecommerce")

conn.commit()
cursor.close()
conn.close()

print("🔥 Database ecommerce been dropped!")
