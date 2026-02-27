package org.personal_project.ecommerce.dao;

import org.personal_project.ecommerce.dto.UserAuthDTO;
import org.personal_project.ecommerce.exceptions.QueryException;
import org.personal_project.ecommerce.util.DBContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public Optional<UserAuthDTO> findAuthInforByUsername(String username){
        String sql = "SELECT userId, password,role FROM users WHERE username = ?";
        Connection conn = DBContext.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    logger.info("Querying authentication Information");
                    return Optional.of(new UserAuthDTO(
                            rs.getString("userId"),
                            rs.getString("role"),
                            rs.getString("password")
                    ));
                }
            }
        }catch (SQLException e){
            throw new QueryException("Error when quering userId and role");
        }
        return Optional.empty();
    }
}