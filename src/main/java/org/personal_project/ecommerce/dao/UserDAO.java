package org.personal_project.ecommerce.dao;

import org.personal_project.ecommerce.dto.UserAuthDTO;
import org.personal_project.ecommerce.enums.DuplicateField;
import org.personal_project.ecommerce.exceptions.DuplicateEntryException;
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

    public Optional<UserAuthDTO> findAuthInfoByUsername(String username){
        String sql = "SELECT user_id, password,role FROM users WHERE username = ?";
        Connection conn = DBContext.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            logger.info("Successful created prepared statement");
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                logger.info("Result set created successfully");
                if (rs.next()) {
                    logger.info("Querying authentication Information");
                    String user_id = rs.getString("user_id");
                    logger.info("Querying user_id successfully");
                    String role = rs.getString("role");
                    logger.info("Querying role successfully");
                    String password = rs.getString("password");
                    logger.info("Querying password successfully");
                    return Optional.of(new UserAuthDTO(
                            user_id,
                            role,
                            password
                    ));
                }
            }catch(SQLException e){
                throw new QueryException("Error when running ResultSet " + e.getMessage());
            }
        }catch (SQLException e){
            throw new QueryException("Error when trying to make prepared statement");
        }
        return Optional.empty();
    }
    public Optional<Integer> insertUser(String username, String password){
        Connection conn = DBContext.getConnection();
        logger.info("Create connection done");
        String sql = "insert into users (username, password) values (?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            logger.info("Insert username and password done");
            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    return Optional.of(rs.getInt(1));
                }
            }catch(SQLException e){
                throw new QueryException(e.getMessage());
            }
        }catch(SQLException e){
            if (e.getErrorCode() == 1062){
                DuplicateField field = DuplicateField.fromErrorMessage(e.getMessage());
                if (field != null){
                    throw new DuplicateEntryException(field.getFriendlyName() + " existed");
                }
            }
        }
        return Optional.empty();
    }
}