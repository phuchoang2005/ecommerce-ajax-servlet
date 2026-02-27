package org.personal_project.ecommerce.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.personal_project.ecommerce.dto.RegisterRequestDTO;
import org.personal_project.ecommerce.dto.RegisterResponseDTO;
import org.personal_project.ecommerce.service.RegisterService;
import org.personal_project.ecommerce.util.MDCUtils;
import org.personal_project.ecommerce.util.SessionUtils;

import java.time.LocalDateTime;
@WebServlet("/auth/register")
public class RegisterServlet extends BaseServlet{
    private final RegisterService registerService = new RegisterService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        RegisterRequestDTO registerRequestDTO = parseJSON(request, RegisterRequestDTO.class);

        MDCUtils.createMDC(request, LocalDateTime.now().toString());

        validateRequired(registerRequestDTO);

        RegisterResponseDTO registerResponseDTO = registerService.register(registerRequestDTO).orElseThrow(() -> new RuntimeException("Error register"));

        sendSuccess(response, "Register Successful", registerResponseDTO);

        SessionUtils.refreshSession(request, registerResponseDTO.getUsername());
    }
}
