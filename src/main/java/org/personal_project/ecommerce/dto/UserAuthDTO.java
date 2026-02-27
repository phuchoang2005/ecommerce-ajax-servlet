package org.personal_project.ecommerce.dto;

public class UserAuthDTO {
    private String userId;
    private String role;
    private String hashedPassword;

    public UserAuthDTO(String userId, String role, String hashedPassword){
        this.userId = userId;
        this.role = role;
        this.hashedPassword = hashedPassword;
    }
    public String getUserId(){return this.userId;}
    public String getRole(){return this.role;}
    public String getHashedPassword(){return this.hashedPassword;}
}
