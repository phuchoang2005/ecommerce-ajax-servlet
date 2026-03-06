package org.personal_project.ecommerce.controller;

import org.personal_project.ecommerce.dto.LoginRequestDTO;
import org.personal_project.ecommerce.dto.LoginResponseDTO;
import org.personal_project.ecommerce.exceptions.AuthenticationException;
import org.personal_project.ecommerce.service.AuthService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.personal_project.ecommerce.util.MDCUtils;
import org.personal_project.ecommerce.util.SessionUtils;


@WebServlet("/auth/login")
public class LoginServlet extends BaseServlet{
    private final AuthService authService = new AuthService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        LoginRequestDTO loginRequestDTO = parseJSON(request, LoginRequestDTO.class);

        MDCUtils.createMDC(request, loginRequestDTO.getUserName());

        validateRequired(loginRequestDTO.getUserName(), loginRequestDTO.getPassword());

        LoginResponseDTO loginResponseDTO = authService.getAuthInfoResponse(loginRequestDTO).orElseThrow(() -> new AuthenticationException("Login Fail"));

        sendSuccess(response, "Login Successful", loginResponseDTO);

        SessionUtils.refreshSession(request, loginRequestDTO.getUserName());
    }
}
