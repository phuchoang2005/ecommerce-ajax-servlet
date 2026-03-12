package org.personal_project.ecommerce.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class DatabaseException extends BaseException{
    public DatabaseException(String message){
        super(
                HttpServletResponse.SC_FORBIDDEN,
                "Forbidden",
                message
        );
    }
}
