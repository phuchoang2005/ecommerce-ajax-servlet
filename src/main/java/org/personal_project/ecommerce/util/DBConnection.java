package org.personal_project.ecommerce.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);
    private static final HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();
            // Cấu hình cơ bản
            config.setJdbcUrl("jdbc:mysql://mysql:3306/ecommerce_new?useUnicode=true&characterEncoding=UTF-8");
            config.setUsername("root");
            config.setPassword("root123");
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            // Cấu hình tối ưu hóa HikariCP (Chuẩn công nghiệp)
            config.setMaximumPoolSize(10); // Tối đa 10 kết nối trong hồ
            config.setMinimumIdle(5);      // Luôn giữ ít nhất 5 kết nối rảnh
            config.setIdleTimeout(300000); // 5 phút
            config.setConnectionTimeout(20000); // Đợi tối đa 20s để lấy kết nối

            // Các tùy chỉnh hiệu năng cho MySQL
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            dataSource = new HikariDataSource(config);
            logger.info("HikariCP DataSource đã được khởi tạo thành công.");
        } catch (Exception e) {
            logger.error("Không thể khởi tạo HikariCP DataSource", e);
            throw new RuntimeException("Lỗi cấu hình Database", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        // Mượn một kết nối từ Pool thay vì tạo mới
        return dataSource.getConnection();
    }
}