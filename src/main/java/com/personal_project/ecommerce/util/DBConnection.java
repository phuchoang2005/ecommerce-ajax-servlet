package com.personal_project.ecommerce.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // 1. Khai báo Logger theo chuẩn SLF4J
    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);

    // Không cần khối static để cấu hình FileHandler ở đây nữa.
    // Việc ghi log ra file sẽ được cấu hình trong file logback.xml hoặc log4j2.xml

    public static Connection getConnection() throws SQLException {
        // Thông tin kết nối (Trong thực tế nên đưa vào file .properties hoặc environment variables)
        String url = "jdbc:mysql://mysql:3306/ecommerce?useUnicode=true&characterEncoding=UTF-8";
        String user = "root";
        String pass = "root123";

        try {
            logger.debug("Đang cố gắng kết nối đến Database: {}", url);

            // Từ JDBC 4.0 trở đi, Class.forName là không bắt buộc nhưng vẫn có thể giữ để đảm bảo
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, pass);

            logger.info("Kết nối Database thành công.");
            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            // 2. Log lỗi theo chuẩn chuyên nghiệp
            // SLF4J tự động xử lý StackTrace nếu bạn truyền đối tượng Exception làm tham số cuối cùng
            logger.error("Lỗi kết nối Database tại URL: {}. Chi tiết lỗi: {}", url, e.getMessage(), e);

            // Re-throw exception để tầng Service có thể xử lý
            throw new SQLException("Không thể kết nối database: " + e.getMessage(), e);
        }
    }
}