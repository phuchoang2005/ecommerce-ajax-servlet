package com.personal_project.ecommerce.dto;
import java.time.LocalDateTime;

public class ApiResponse<T> {
    private String timestamp;
    private int status;
    private String message;
    private T data;

    public ApiResponse(int status, String message, T data){
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public int getStatus(){return this.status;}
    public String getMessage(){return this.message;}
}
