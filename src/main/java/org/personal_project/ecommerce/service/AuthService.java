package com.personal_project.ecommerce.service;

import com.personal_project.ecommerce.dto.LoginRequestDTO;
import com.personal_project.ecommerce.dto.LoginResponseDTO;
import com.personal_project.ecommerce.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final UserRepository userRepository = new UserRepository();

    private boolean authenticate(LoginRequestDTO loginRequestDTO) throws SQLException{
        String username = loginRequestDTO.getUserName();
        String rawPassword = loginRequestDTO.getPassword();

        logger.debug("Bắt đầu quá trình xác thực cho user: {}", loginRequestDTO.getUserName());

        return userRepository.getHashedPassword(username)
                .map(hashPassword ->{
                    logger.info("User exist");
                    return BCrypt.checkpw(rawPassword, hashPassword);
                }
                ).orElseGet(() ->{
                   logger.warn("User doens't exist: {}", username);
                   return false;
                });
    }
    public Optional<LoginResponseDTO> getResponse(LoginRequestDTO loginRequestDTO)throws SQLException{
        boolean isMatched = authenticate(loginRequestDTO);
        String username = loginRequestDTO.getUserName();
        if (isMatched){
            logger.info("Login Sucessful by user: {}", username);
            return userRepository.getAuthInfor(username);
        }
        return Optional.empty();
    }

}