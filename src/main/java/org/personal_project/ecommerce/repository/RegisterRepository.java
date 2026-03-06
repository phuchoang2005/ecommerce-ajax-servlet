package org.personal_project.ecommerce.repository;

import org.personal_project.ecommerce.dao.ProfilesDAO;
import org.personal_project.ecommerce.dao.UserDAO;
import org.personal_project.ecommerce.dto.RegisterRequestDTO;

import java.util.Optional;
public class RegisterRepository {
    private final UserDAO userDAO = new UserDAO();
    private final ProfilesDAO profilesDAO = new ProfilesDAO();
    public Optional<Integer> saveUser(String username, String hashedPasswordString){
        return userDAO.insertUser(username, hashedPasswordString);
    }
    public void insertProfile(RegisterRequestDTO registerRequestDTO, int user_id){
        profilesDAO.insertProfile(registerRequestDTO, user_id);
    }
}
