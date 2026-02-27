package org.personal_project.ecommerce.dto;

public class LoginResponseDTO {
    private String userId;
    private String username;
    private String role;

    public LoginResponseDTO(String username, String role, String userId){
        this.username = username;
        this.role = role;
        this.userId = userId;
    }
}
