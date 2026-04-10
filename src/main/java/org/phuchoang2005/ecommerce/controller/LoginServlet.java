package org.phuchoang2005.ecommerce.controller;

import org.phuchoang2005.ecommerce.dto.LoginRequestDTO;
import org.phuchoang2005.ecommerce.dto.LoginResponseDTO;
import org.phuchoang2005.ecommerce.enums.HttpStatusEnum;
import org.phuchoang2005.ecommerce.exceptions.authentication.AuthenticationException;
import org.phuchoang2005.ecommerce.service.AuthService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.phuchoang2005.ecommerce.util.MDCUtils;
import org.phuchoang2005.ecommerce.util.SessionUtils;


@WebServlet("/auth/login")
public class LoginServlet extends BaseServlet{
    private final AuthService authService = new AuthService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        LoginRequestDTO loginRequestDTO = parseJSON(request, LoginRequestDTO.class);

        MDCUtils.createMDC(request, loginRequestDTO.getUserName());

        validateRequired(loginRequestDTO.getUserName(), loginRequestDTO.getPassword());

        LoginResponseDTO loginResponseDTO = authService.checkAuthentication(loginRequestDTO).orElseThrow(() -> new AuthenticationException("Login Fail"));

        sendResponse(response, HttpStatusEnum.SUCCESS.code(), HttpStatusEnum.SUCCESS.message(), loginResponseDTO);

        SessionUtils.refreshSession(request, loginRequestDTO.getUserName());
    }
}
