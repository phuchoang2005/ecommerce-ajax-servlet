package com.personal_project.ecommerce.service;

import com.personal_project.ecommerce.dto.LoginRequestDTO;
import com.personal_project.ecommerce.util.DBConnection;
import com.personal_project.ecommerce.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final UserRepository userRepository = new UserRepository();

    public boolean authenticate(LoginRequestDTO loginRequestDTO) {
        logger.debug("Bắt đầu quá trình xác thực cho user: {}", loginRequestDTO.getUserName());

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                String hashPassword = userRepository.getHashedPassword(conn, loginRequestDTO.getUserName());
                if (hashPassword == null) {
                    logger.warn("Xác thực thất bại: User {} không tồn tại", loginRequestDTO.getUserName());
                    conn.commit();
                    return false;
                }

                boolean isMatched = BCrypt.checkpw(loginRequestDTO.getPassword(), hashPassword);
                if (isMatched) {
                    logger.info("Đăng nhập THÀNH CÔNG cho user: {}", loginRequestDTO.getUserName());
                    conn.commit();
                    return true;
                } else {
                    logger.warn("Đăng nhập THẤT BẠI: Sai mật khẩu cho user: {}", loginRequestDTO.getUserName());
                    conn.commit();
                    return false;
                }
            } catch (Exception e) {
                conn.rollback();
                logger.error("Lỗi xảy ra trong quá trình xử lý Transaction, đã thực hiện Rollback", e);
                throw e;
            }
        } catch (SQLException e) {
            logger.error("Lỗi kết nối cơ sở dữ liệu từ HikariCP", e);
            return false;
        }
    }
}