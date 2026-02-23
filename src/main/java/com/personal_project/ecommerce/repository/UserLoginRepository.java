package com.personal_project.ecommerce.repository;

import com.personal_project.ecommerce.dto.UserLoginDTO;
import com.personal_project.ecommerce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserLoginRepository {
    public boolean isExisted(UserLoginDTO userLoginDTO) {
        boolean result = false;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userLoginDTO.getUsername());
            ps.setString(2, userLoginDTO.getPassword());

            ResultSet rs = ps.executeQuery();
            result = rs.next();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
