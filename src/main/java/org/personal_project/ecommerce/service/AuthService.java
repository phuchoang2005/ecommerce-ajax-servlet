package org.personal_project.ecommerce.service;

import org.personal_project.ecommerce.dto.LoginRequestDTO;
import org.personal_project.ecommerce.dto.LoginResponseDTO;
import org.personal_project.ecommerce.exceptions.AuthenticationException;
import org.personal_project.ecommerce.repository.AuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final AuthRepository authRepository = new AuthRepository();

    public Optional<LoginResponseDTO> getAuthInfoResponse(LoginRequestDTO loginRequestDTO){
        String username = loginRequestDTO.getUserName();
        String rawPassword = loginRequestDTO.getPassword();

        return Optional.of(authRepository.getAuthInfo(username)
                .filter(user -> BCrypt.checkpw(rawPassword, user.getHashedPassword()))
                .map(user -> new LoginResponseDTO(user.getUserId(), user.getRole(), username))
                .orElseThrow(() -> new AuthenticationException("Username or Password invalid")));
    }
}