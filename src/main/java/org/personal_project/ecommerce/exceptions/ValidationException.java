package org.personal_project.ecommerce.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class ValidationException extends BaseException{
    public ValidationException(String message){
        super(
                HttpServletResponse.SC_BAD_REQUEST,
                "Bad Request",
                message
        );
    }
}
