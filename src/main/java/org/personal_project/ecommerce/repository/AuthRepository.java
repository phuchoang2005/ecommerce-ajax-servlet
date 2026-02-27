package org.personal_project.ecommerce.repository;

import org.personal_project.ecommerce.dao.UserDAO;
import org.personal_project.ecommerce.dto.LoginResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

public class UserRepository {
    private final UserDAO userDAO = new UserDAO();
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public Optional<String> getHashedPassword(String username){
            logger.info("System is find password");
            return userDAO.findHashedPasswordByUsername(username);
    }
    public Optional<LoginResponseDTO> getAuthInfor(String username){
            logger.info("System is loading authentication information");
            return userDAO.findAuthInforByUsername(username);
    }
}
