package org.phuchoang2005.ecommerce.service;

import org.phuchoang2005.ecommerce.dto.LoginRequestDTO;
import org.phuchoang2005.ecommerce.dto.LoginResponseDTO;
import org.phuchoang2005.ecommerce.exceptions.authentication.AuthenticationException;
import org.phuchoang2005.ecommerce.repository.AuthRepository;
import org.phuchoang2005.ecommerce.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final AuthRepository authRepository = new AuthRepository();

    public Optional<LoginResponseDTO> checkAuthentication(LoginRequestDTO loginRequestDTO){
        logger.info("[SERVICE] Start Usecase Authentication");

        logger.info("[SERVICE] Get usename");
        String username = loginRequestDTO.getUserName();
        logger.info("[SERVICE]: Get password");
        String rawPassword = loginRequestDTO.getPassword();

        logger.info("[SERVICE] CHECKING VALIDATION");

        Optional<LoginResponseDTO> result = Optional.of(authRepository.findAuthInforByUsername(username)
                .filter(user -> PasswordUtil.verify(rawPassword, user.getHashedPassword()))
                .map(user -> new LoginResponseDTO(username, user.getRole(), user.getUserId()))
                .orElseThrow(() -> new AuthenticationException("Username or Password invalid")));
        logger.info("[SERVICE]End Usecase Authentication");
        return result;
    }
}