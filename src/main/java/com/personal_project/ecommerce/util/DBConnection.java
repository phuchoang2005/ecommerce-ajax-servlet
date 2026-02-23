package com.personal_project.ecommerce.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DBConnection {

    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("db-error.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace(); // fallback nếu không tạo được file log
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://mysql:3306/ecommerce?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String pass = "root123";

            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            logger.severe("Database connection error: " + e.getMessage());
            logger.severe(getStackTrace(e));
            throw new SQLException("Khong the ket noi database", e);
        }
    }

    private static String getStackTrace(Exception e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement el : e.getStackTrace()) {
            sb.append(el.toString()).append("\n");
        }
        return sb.toString();
    }
}
