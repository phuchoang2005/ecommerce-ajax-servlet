package org.personal_project.ecommerce.dto;

public class RegisterRequestDTO {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String address;
    private String phone;


    public String getUsername(){return this.username;}
    public String getFullname(){return this.fullname;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public String getAddress(){return this.address;}
    public String getPhone(){return this.phone;}
}
