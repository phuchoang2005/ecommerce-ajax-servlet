package org.personal_project.ecommerce.service;

import org.personal_project.ecommerce.dto.RegisterRequestDTO;
import org.personal_project.ecommerce.dto.RegisterResponseDTO;
import org.personal_project.ecommerce.repository.RegisterRepository;
import org.personal_project.ecommerce.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class RegisterService {
    private final RegisterRepository registerRepository = new RegisterRepository();
    private final Logger logger  = LoggerFactory.getLogger(RegisterService.class);

    public Optional<RegisterResponseDTO> register(RegisterRequestDTO registerRequestDTO){

        String username = registerRequestDTO.getUsername();
        logger.info("Get username done");
        String password = registerRequestDTO.getPassword();
        logger.info("Get password done");
        String email = registerRequestDTO.getEmail();
        logger.info("Get email done");

        logger.info("Beginning Register");
        
        logger.info("Beginning hash password");
        String hashedPasswordString = PasswordUtil.hash(password);

        logger.info("encoding password done");
        
        Optional<Integer> user_id = registerRepository.saveUser(username, hashedPasswordString);

        logger.info("Save user done");

        Integer id = user_id.orElseThrow(() -> new RuntimeException("ID not found for create profile"));

        logger.info("Load ID user for signup done");

        registerRepository.insertProfile(registerRequestDTO, id);

        logger.info("Insert Profile done");

        return Optional.of(new RegisterResponseDTO(username, email));
    }
}
