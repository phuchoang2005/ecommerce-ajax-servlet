package org.phuchoang2005.ecommerce.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.phuchoang2005.ecommerce.dto.RegisterRequestDTO;
import org.phuchoang2005.ecommerce.dto.RegisterResponseDTO;
import org.phuchoang2005.ecommerce.enums.HttpStatusEnum;
import org.phuchoang2005.ecommerce.service.RegisterService;
import org.phuchoang2005.ecommerce.util.MDCUtils;
import org.phuchoang2005.ecommerce.util.SessionUtils;

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

        sendResponse(response, HttpStatusEnum.CREATED.code(),HttpStatusEnum.CREATED.message(), registerResponseDTO);

        SessionUtils.refreshSession(request, registerResponseDTO.getUsername());
    }
}
