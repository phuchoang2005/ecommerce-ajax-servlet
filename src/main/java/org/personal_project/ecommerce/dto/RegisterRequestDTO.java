package org.personal_project.ecommerce.dto;

public class RegisterRequestDTO {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String address;
    private String phone;

    public RegisterRequestDTO(String username, String fullname, String email, String password, String address, String phone){
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }
    public String getUsername(){return this.username;}
    public String getFullname(){return this.fullname;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public String getAddress(){return this.address;}
    public String getPhone(){return this.phone;}
}
