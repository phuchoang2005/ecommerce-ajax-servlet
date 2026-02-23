package com.personal_project.ecommerce.dto;

public class UserLoginDTO {

    private String username;
    private String password;

    public UserLoginDTO(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}

}
