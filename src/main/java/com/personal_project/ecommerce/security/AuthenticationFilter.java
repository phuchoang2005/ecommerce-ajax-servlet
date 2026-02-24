package com.personal_project.ecommerce.security;

import com.google.gson.Gson;
import com.personal_project.ecommerce.dto.ApiResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (isLoggedIn){
            MDC.put("user", session.getAttribute("user").toString());
            filterChain.doFilter(request, response);
        }else{
            logger.warn("Stop Login");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType("application/json");
            Gson gson = new Gson();
            res.getWriter().write(gson.toJson(new ApiResponse<>(HttpServletResponse.SC_UNAUTHORIZED, "Please login first", null)));
        }
    }
}
