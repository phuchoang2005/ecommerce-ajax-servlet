package com.personal_project.ecommerce.repository;

import com.personal_project.ecommerce.util.DBConnection;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public String getHashedPassword(String username){
        logger.debug("Querying password for user",username);
        String sql = "select password from users where username = ?";
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return rs.getString("password");
                }else{
                    logger.warn("User doesn't exist");
                }
            }
        }catch(SQLException e){
            logger.error("Error when trying to query database: {}", username, e);
        }
        return null;
    }
}
