package org.personal_project.ecommerce.controller;

import com.google.gson.Gson;

import com.google.gson.JsonParseException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.personal_project.ecommerce.api.ApiResponse;
import org.personal_project.ecommerce.exceptions.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public abstract class BaseServlet extends HttpServlet{
    private static final Logger logger = LoggerFactory.getLogger(BaseServlet.class);

    protected void validateRequired(Object ... params){
        for (Object p : params){
            if (p == null|| (p instanceof String && ((String) p).isEmpty())){
                throw new ValidationException("Missing information");
            }
        }
        logger.info("[CONTROLLER] Checking validate successfully");
    }
    protected void sendResponse(HttpServletResponse response, int status, String message, Object object){
        try{
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(new Gson().toJson(new ApiResponse<>(status, message, object)));
            logger.info("[HTTP] Response sent: Status: {}, Message: {}", status, message);
        }catch(IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    protected <T> T parseJSON(HttpServletRequest request, Class<T> clazz) {

        try {

            logger.info("[HTTP] Request from {}", request.getRemoteAddr());

            BufferedReader reader = request.getReader();

            String body = reader.lines().collect(Collectors.joining());

            T obj = new Gson().fromJson(body, clazz);

            if(obj == null){
                throw new JsonParseException("JSON body is empty or invalid");
            }

            return obj;

        } catch (IOException e) {

            logger.error("[CONTROLLER] Cannot read JSON", e);

            throw new JsonParseException("JSON parsing error");
        }
    }
}
