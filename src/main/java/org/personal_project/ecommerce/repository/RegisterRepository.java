package org.personal_project.ecommerce.repository;

import org.personal_project.ecommerce.dao.ProfilesDAO;
import org.personal_project.ecommerce.dao.UserDAO;
import org.personal_project.ecommerce.dto.RegisterRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
public class RegisterRepository {
    private final UserDAO userDAO = new UserDAO();
    private final ProfilesDAO profilesDAO = new ProfilesDAO();
    private final static Logger logger = LoggerFactory.getLogger(RegisterRepository.class);
    public Optional<Integer> saveUser(String username, String hashedPasswordString){
        logger.info("[REPOSITORY]Query userID");
        Optional<Integer> result = userDAO.insertUser(username, hashedPasswordString);
        logger.info("[REPOSITORY]Result");
        return result;
    }
    public void insertProfile(RegisterRequestDTO registerRequestDTO, int user_id){
        logger.info("[REPOSITORY]Execute inserting user");
        profilesDAO.insertProfile(registerRequestDTO, user_id);
        logger.info("[REPOSITORY]Result");
    }
}
