package org.personal_project.ecommerce.repository;

import org.personal_project.ecommerce.dao.UserDAO;
import org.personal_project.ecommerce.dto.UserAuthDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

public class AuthRepository {
    private final UserDAO userDAO = new UserDAO();
    private static final Logger logger = LoggerFactory.getLogger(AuthRepository.class);

    public Optional<UserAuthDTO> getAuthInfor(String username){
            logger.info("System is loading authentication information");
            return userDAO.findAuthInforByUsername(username);
    }
}
