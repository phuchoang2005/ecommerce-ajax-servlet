package com.personal_project.ecommerce.exceptions;

import com.personal_project.ecommerce.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationException extends BaseException {
    public AuthenticationException(String message, String path){
        super(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                message,
                path
        );
    }
    @Override
    public ApiErrorResponse getApiErrorResponse(){return this.apiErrorResponse;}
}
