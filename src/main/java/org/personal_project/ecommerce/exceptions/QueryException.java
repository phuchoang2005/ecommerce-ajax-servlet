package org.personal_project.ecommerce.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class QueryException extends BaseException{
    public QueryException(String message){
        super(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                message
        );
    }
}
