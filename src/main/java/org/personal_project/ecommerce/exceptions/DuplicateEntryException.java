package org.personal_project.ecommerce.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class DuplicateEntryException extends BaseException{
    public DuplicateEntryException(String message){
        super(
                HttpServletResponse.SC_FORBIDDEN,
                "Forbidden",
                message
        );
    }
}
