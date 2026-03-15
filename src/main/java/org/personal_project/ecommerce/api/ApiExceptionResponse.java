package org.personal_project.ecommerce.api;

import java.time.LocalDateTime;

public class ApiExceptionResponse extends RuntimeException{
    private final String timestamp;
    private final int status;
    private final String error;
    private final String path;

    public ApiExceptionResponse(int status, String error, String message, String path){
        super(message);
        this.error = error;
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.path = path;
    }
    public int getStatus(){return this.status;}
    public String getMessage(){return super.getMessage();}
}
