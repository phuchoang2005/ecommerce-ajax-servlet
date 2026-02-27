package org.personal_project.ecommerce.dto;

import java.time.LocalDateTime;

public class RegisterResponseDTO {
    private final String timestamp;
    private final String username;
    private final String email;

    public RegisterResponseDTO(String username, String email){
        this.timestamp = LocalDateTime.now().toString();
        this.username = username;
        this.email = email;
    }

    public String getUsername(){return this.username;}
    public String getEmail(){return this.email;}
}
