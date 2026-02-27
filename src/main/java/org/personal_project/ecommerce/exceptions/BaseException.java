package org.personal_project.ecommerce.exceptions;


public class BaseException extends RuntimeException {
    private final int statusCode;
    private final String error;
    public BaseException(int statusCode, String error, String message){
        super(message);
        this.statusCode = statusCode;
        this.error = error;
    }
    public int getStatusCode(){return this.statusCode;}
    public String getError(){return this.error;}
}
