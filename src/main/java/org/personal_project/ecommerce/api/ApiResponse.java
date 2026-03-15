package org.personal_project.ecommerce.api;
import java.time.LocalDateTime;

public class ApiResponse<T> {
    private final String timestamp;
    private final int status;
    private final String message;
    private final T data;

    public ApiResponse(int status, String message, T data){
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public int getStatus(){return this.status;}
    public String getMessage(){return this.message;}
}
