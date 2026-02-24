package com.personal_project.ecommerce.service;

import com.personal_project.ecommerce.dto.LoginRequestDTO;
import com.personal_project.ecommerce.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private UserRepository userRepository = new UserRepository();
    public boolean authenticate(LoginRequestDTO loginRequestDTO){
        logger.debug("Beginnig authentication");
        String hashPassword = userRepository.getHashedPassword(loginRequestDTO.getUserName());
        if (hashPassword == null){
            logger.warn("Authenticate fail: Username {}. User doesn't exist", loginRequestDTO.getUserName());
            return false;
        }
        boolean isMatched = BCrypt.checkpw(loginRequestDTO.getPassword(), hashPassword);
        if (isMatched){
            logger.info("Login Successfully for User {}", loginRequestDTO.getUserName());
            return true;
        }else{
            logger.warn("Login fail for User: {}", loginRequestDTO.getUserName());
            return false;
        }
    }
}
