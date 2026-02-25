package com.personal_project.ecommerce.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public String getHashedPassword(Connection conn, String username) throws SQLException {
        String sql = "SELECT password FROM users WHERE username = ?";

        // Không đóng Connection ở đây (Service sẽ đóng)
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    logger.info("Querying password");
                    return rs.getString("password");
                }
            }
        }
        return null;
    }
}