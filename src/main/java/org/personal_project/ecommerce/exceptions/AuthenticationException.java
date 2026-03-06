package org.personal_project.ecommerce.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationException extends BaseException {
    public AuthenticationException(String message){
        super(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                message
        );
    }
}
