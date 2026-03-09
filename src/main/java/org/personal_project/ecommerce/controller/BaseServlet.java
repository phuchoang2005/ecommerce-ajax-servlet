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
        logger.info("Checking validate sucessful");
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
    protected <T> T parseJSON(HttpServletRequest request, Class<T> clazz) {

    try {

        logger.info("Request from {}", request.getRemoteAddr());

        BufferedReader reader = request.getReader();

        String body = reader.lines().collect(Collectors.joining());

        logger.info("RAW BODY: {}", body);

        T obj = new Gson().fromJson(body, clazz);

        if(obj == null){
            throw new RuntimeException("JSON body is empty or invalid");
        }

        return obj;

    } catch (IOException e) {

        logger.error("Cannot read JSON", e);

        throw new RuntimeException("JSON parsing error");
    }
}
}
