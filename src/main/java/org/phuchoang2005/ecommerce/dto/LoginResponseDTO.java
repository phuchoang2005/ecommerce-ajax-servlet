package org.phuchoang2005.ecommerce.dto;

public class LoginResponseDTO {
    private String user_id;
    private String username;
    private String role;

    public LoginResponseDTO(String username, String role, String user_id){
        this.username = username;
        this.role = role;
        this.user_id = user_id;
    }
}
