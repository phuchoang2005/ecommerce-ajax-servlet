package com.personal_project.ecommerce.exceptions;

import com.personal_project.ecommerce.dto.ApiErrorResponse;

public abstract class BaseException extends RuntimeException {
    protected ApiErrorResponse apiErrorResponse;
    public BaseException(int statusCode, String error, String message, String path){
        super(message);
        this.apiErrorResponse = new ApiErrorResponse(
                statusCode,
                error,
                message,
                path
        );
    }
    public abstract ApiErrorResponse getApiErrorResponse();
}
