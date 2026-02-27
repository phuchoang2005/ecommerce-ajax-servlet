import mysql.connector

DB_CONFIG = {
    'host': 'localhost',
    'user': 'root',          # Thay bằng username của bạn
    'password': 'root123',   # Thay bằng password của bạn
    'database': 'ecommerce_new'
}

SQL_FILE_PATH = 'dummy_data.sql'

def import_sql():
    connection = None
    cursor = None
    try:
        connection = mysql.connector.connect(**DB_CONFIG)
        cursor = connection.cursor()

        with open(SQL_FILE_PATH, 'r', encoding='utf-8') as file:
            sql_commands = file.read()

        # Tách thủ công các câu lệnh bằng dấu chấm phẩy (;) để tránh lỗi tham số thư viện
        sql_statements = sql_commands.split(';')

        for statement in sql_statements:
            clean_statement = statement.strip()
            if clean_statement:
                cursor.execute(clean_statement)

        connection.commit()
        print("✅ Import dữ liệu thành công!")

    except Exception as e:
        print(f"❌ Lỗi: {e}")
    finally:
        if cursor is not None:
            cursor.close()
        if connection is not None and connection.is_connected():
            connection.close()

if __name__ == '__main__':
    import_sql()