package org.personal_project.ecommerce.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class InputOutputException extends BaseException{
    public InputOutputException(String message){
        super(
                HttpServletResponse.SC_BAD_GATEWAY,
                "Bad Gateway",
                message
        );
    }
}
