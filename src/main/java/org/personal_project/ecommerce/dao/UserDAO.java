package com.personal_project.ecommerce.dao;

import com.personal_project.ecommerce.dto.LoginResponseDTO;
import com.personal_project.ecommerce.util.DBContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public Optional<String> findHashedPasswordByUsername(String username) throws SQLException {
        String sql = "SELECT password FROM users WHERE username = ?";
        Connection conn = DBContext.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    logger.info("Querying password");
                    return Optional.of(rs.getString("password"));
                }
            }
        }
        return Optional.empty();
    }
    public Optional<LoginResponseDTO> findAuthInforByUsername(String username) throws SQLException{
        String sql = "SELECT userId, role FROM users WHERE username = ?";
        Connection conn = DBContext.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    logger.info("Querying authentication Information");
                    return Optional.of(new LoginResponseDTO(rs.getString("userId"), rs.getString("role"), username));
                }
            }
        }
        return Optional.empty();
    }
}