package org.personal_project.ecommerce.dao;

import org.personal_project.ecommerce.dto.RegisterRequestDTO;
import org.personal_project.ecommerce.enums.DuplicateField;
import org.personal_project.ecommerce.exceptions.DuplicateEntryException;
import org.personal_project.ecommerce.util.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfilesDAO {
    public void insertProfile(RegisterRequestDTO registerRequestDTO, int user_id){
        Connection conn = DBContext.getConnection();
        String sql = "insert into profiles (user_id, full_name, email, phone, address) values (?,?,?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, user_id);
            ps.setString(2, registerRequestDTO.getFullname());
            ps.setString(3, registerRequestDTO.getEmail());
            ps.setString(4, registerRequestDTO.getPhone());
            ps.setString(5, registerRequestDTO.getAddress());
            ps.executeUpdate();
        }catch(SQLException e){
            if (e.getErrorCode() == 1062){
                DuplicateField field = DuplicateField.fromErrorMessage(e.getMessage());
                if (field != null){
                    throw new DuplicateEntryException(field.getFriendlyName() + " existed");
                }
            }else{
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
