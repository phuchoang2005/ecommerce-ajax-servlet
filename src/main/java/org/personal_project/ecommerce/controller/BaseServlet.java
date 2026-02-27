package org.personal_project.ecommerce.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.personal_project.ecommerce.dto.ApiResponse;
import org.personal_project.ecommerce.exceptions.InputOutputException;
import org.personal_project.ecommerce.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class BaseServlet extends HttpServlet{
    private static final Logger logger = LoggerFactory.getLogger(BaseServlet.class);

    protected void validateRequired(Object ... params){
        for (Object p : params){
            if (p == null|| (p instanceof String && ((String) p).isEmpty())){
                throw new ValidationException("Missing information");
            }
        }
    }
    protected void sendSuccess(HttpServletResponse response, String message, Object object){
        try{
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(new Gson().toJson(new ApiResponse<>(HttpServletResponse.SC_OK, message, object)));
            logger.info("Response sent: Status: {}, Message: {}", HttpServletResponse.SC_OK, message);
        }catch(IOException e){
            throw new InputOutputException(e.getMessage());
        }
    }
    protected <T> T parseJSON(HttpServletRequest request, Class<T> _class) {
        try {
            logger.info("Request sent from: {}", request.getRemoteAddr());
            return new Gson().fromJson(request.getReader(), _class);
        }catch(IOException e){
            throw new InputOutputException("Get JSON Error");
        }
    }
}
