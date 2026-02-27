package com.personal_project.ecommerce.exceptions;

import com.personal_project.ecommerce.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletResponse;

public class ValidationException extends BaseException{
    public ValidationException(String message, String path){
        super(
                HttpServletResponse.SC_BAD_REQUEST,
                "Bad Request",
                message,
                path
        );
    }
    @Override
    public ApiErrorResponse getApiErrorResponse(){return this.apiErrorResponse;}
}
