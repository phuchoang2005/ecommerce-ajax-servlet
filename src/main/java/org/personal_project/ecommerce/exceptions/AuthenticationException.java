package org.personal_project.ecommerce.exceptions;

import org.personal_project.ecommerce.dto.ApiErrorResponse;
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
