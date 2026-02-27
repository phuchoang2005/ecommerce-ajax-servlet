package com.personal_project.ecommerce.controller;
import com.google.gson.Gson;
import com.personal_project.ecommerce.dto.ApiResponse;
import com.personal_project.ecommerce.dto.LoginRequestDTO;
import com.personal_project.ecommerce.dto.LoginResponseDTO;
import com.personal_project.ecommerce.exceptions.AuthenticationException;
import com.personal_project.ecommerce.exceptions.ValidationException;
import com.personal_project.ecommerce.service.AuthService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        LoginRequestDTO loginRequestDTO = getLoginRequest(request);

        MDC.put("username", loginRequestDTO.getUserName());

        checkMissingInformation(request, loginRequestDTO);

        try {
            checkAuthenticate(request, response, loginRequestDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkAuthenticate(HttpServletRequest request, HttpServletResponse response, LoginRequestDTO loginRequestDTO) throws SQLException{
        AuthService authService = new AuthService();
        try{
            authService.getResponse(loginRequestDTO)
                    .ifPresentOrElse(loginResponseDTO -> {
                        setNewSession(request, loginRequestDTO.getUserName());
                        ApiResponse<LoginResponseDTO> apiResponse = new ApiResponse<>(HttpServletResponse.SC_OK, "Login Sucessfull", loginResponseDTO);
                        try {
                            sendResponse(response, apiResponse);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }, () -> {
                        throw new AuthenticationException("Username or Password not found", request.getRequestURI());
                    });
        }catch(SQLException e){
            throw new RuntimeException("Error Query", e);
        }
    }
    private void checkMissingInformation(HttpServletRequest request, LoginRequestDTO loginRequestDTO) throws IOException{
        if (loginRequestDTO.getUserName() == null || loginRequestDTO.getPassword() == null){
            throw new ValidationException("Missing information", request.getRequestURI());
        }
    }
    private void sendResponse(HttpServletResponse response, ApiResponse<LoginResponseDTO> apiResponse) throws IOException {
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
