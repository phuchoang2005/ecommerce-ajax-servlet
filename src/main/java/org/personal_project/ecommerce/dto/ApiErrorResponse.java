package org.personal_project.ecommerce.dto;

import java.time.LocalDateTime;

public class ApiErrorResponse {
    private String timestamp;
    private int status;
    private String message;
    private String error;
    private String path;

    public ApiErrorResponse(int status, String error, String message, String path){
        this.error = error;
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.message = message;
        this.path = path;
    }
    public int getStatus(){return this.status;}
    public String getMessage(){return this.message;}
}
