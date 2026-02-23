package com.personal_project.ecommerce.controller;

import com.personal_project.ecommerce.service.UserService;
import com.personal_project.ecommerce.dto.UserLoginDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/general/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/plain; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");

        response.getWriter().write(new UserService().checkLogin(new UserLoginDTO(username, password)));
    }
}
