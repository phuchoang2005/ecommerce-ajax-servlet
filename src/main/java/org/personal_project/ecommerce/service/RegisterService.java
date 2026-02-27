package org.personal_project.ecommerce.service;

import org.personal_project.ecommerce.dto.RegisterRequestDTO;
import org.personal_project.ecommerce.dto.RegisterResponseDTO;
import org.personal_project.ecommerce.repository.RegisterRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class RegisterService {
    private final RegisterRepository registerRepository = new RegisterRepository();

    public Optional<RegisterResponseDTO> register(RegisterRequestDTO registerRequestDTO){
        String username = registerRequestDTO.getUsername();
        String password = registerRequestDTO.getPassword();
        String email = registerRequestDTO.getEmail();

        Optional<Integer> user_id = registerRepository.saveUser(username, new BCryptPasswordEncoder().encode(password));

        Integer id = user_id.orElseThrow(() -> new RuntimeException("ID not found for create profile"));

        registerRepository.insertProfile(registerRequestDTO, id);

        return Optional.of(new RegisterResponseDTO(username, email));
    }
}
