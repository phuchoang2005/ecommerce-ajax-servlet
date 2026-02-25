package com.personal_project.ecommerce.controller;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.personal_project.ecommerce.dto.ApiResponse;
import com.personal_project.ecommerce.dto.LoginRequestDTO;
import com.personal_project.ecommerce.service.AuthService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            LoginRequestDTO loginRequestDTO = getLoginRequest(request);

            MDC.put("username", loginRequestDTO.getUserName());

            if (!checkMissingInformation(loginRequestDTO)){
                sendResponse(response, new ApiResponse<>(HttpServletResponse.SC_BAD_REQUEST, "Missing information for login", null));
                return;
            }

            checkAuthenticate(request, response, loginRequestDTO);
        }catch(JsonSyntaxException e){
            logger.error("JSON invalid", e);
            sendResponse(response, new ApiResponse<>(HttpServletResponse.SC_BAD_REQUEST, "JSON invalid", null));
        }catch (Exception e){
            sendResponse(response, new ApiResponse<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error System", null));
        }
    }

    private void checkAuthenticate(HttpServletRequest request, HttpServletResponse response, LoginRequestDTO loginRequestDTO) throws IOException{
        AuthService authService = new AuthService();
        if (authService.authenticate(loginRequestDTO)){
            setNewSession(request, loginRequestDTO.getUserName());
            logger.info("Loging sucessfully by {}", loginRequestDTO.getUserName());
            sendResponse(response, new ApiResponse<>(HttpServletResponse.SC_OK, "Login Sucessful", loginRequestDTO.getUserName()));
        }else{
            logger.warn("Wrong credentials by User: {}", loginRequestDTO.getUserName());
            sendResponse(response, new ApiResponse<>(HttpServletResponse.SC_UNAUTHORIZED, "Wrong username or password", null));
        }
    }
    private boolean checkMissingInformation(LoginRequestDTO loginRequestDTO){
        if (loginRequestDTO.getUserName() == null || loginRequestDTO.getPassword() == null){
            logger.warn("Login Fail: Missing inforamtion (bad request)");
            return false;
        }
        return true;
    }
    private void sendResponse(HttpServletResponse response, ApiResponse<Object> apiResponse) throws IOException {
        response.setStatus(apiResponse.getStatus());
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(apiResponse));
        logger.debug("Response sent: Status: {}, Message: {}", apiResponse.getStatus(), apiResponse.getMessage());
    }
    private LoginRequestDTO getLoginRequest(HttpServletRequest request) throws IOException {
        logger.info("Request sent from: {}", request.getRemoteAddr());
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        return gson.fromJson(reader, LoginRequestDTO.class);
    }
    private void setNewSession(HttpServletRequest request, String username){
        logger.info("Create new session for username {}", username);
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null){
            oldSession.invalidate();
        }
        HttpSession newSession = request.getSession(true);
        newSession.setAttribute("user", username);
    }
}
