import mysql.connector
from mysql.connector import Error
import time

# --- CẤU HÌNH DATABASE CỦA BẠN Ở ĐÂY ---
DB_CONFIG = {
    'host': 'localhost',
    'user': 'root',          # Thay bằng username của bạn
    'password': 'root123',  # Thay bằng mật khẩu của bạn
    'database': 'ecommerce_new'   # Thay bằng tên database bạn đã tạo schema
}

SQL_FILE_PATH = 'dummy_data.sql'

def import_sql_fast():
    try:
        print("Đang kết nối tới MySQL...")
        connection = mysql.connector.connect(**DB_CONFIG)

        if connection.is_connected():
            print(f"✅ Kết nối thành công tới database '{DB_CONFIG['database']}'")
            cursor = connection.cursor()

            # Đọc nội dung file SQL
            print(f"Đang đọc file {SQL_FILE_PATH}...")
            with open(SQL_FILE_PATH, 'r', encoding='utf-8') as file:
                sql_commands = file.read()

            print("Đang thực thi các câu lệnh INSERT...")
            start_time = time.time()

            # Tham số multi=True cho phép chạy nhiều câu lệnh (phân tách bởi dấu ;) cùng lúc
            results = cursor.execute(sql_commands)

            # Phải lặp qua generator trả về để các lệnh thực sự được thực thi
            for result in results:
                pass

            # Xác nhận lưu thay đổi vào database
            connection.commit()

            end_time = time.time()
            print(f"✅ Hoàn tất import dữ liệu! Thời gian chạy: {round(end_time - start_time, 2)} giây.")

    except Error as e:
        print(f"❌ Lỗi khi thao tác với MySQL: {e}")
    except FileNotFoundError:
        print(f"❌ Không tìm thấy file '{SQL_FILE_PATH}'. Bạn đã chạy script tạo dữ liệu chưa?")
    finally:
        if 'connection' in locals() and connection.is_connected():
            cursor.close()
            connection.close()
            print("Đã đóng kết nối MySQL.")

if __name__ == '__main__':
    import_sql_fast()