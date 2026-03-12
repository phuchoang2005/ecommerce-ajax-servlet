package org.personal_project.ecommerce.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;

public class AuthenticationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (isLoggedIn){
            MDC.put("user", session.getAttribute("user").toString());
            try{
                logger.info(">> BEGIN AUTHENTICATION FILTER");
                filterChain.doFilter(request, response);
            }finally{
                logger.info("<< CLOSE AUTHENTICATION FILTER");
            }
        }else{
            logger.warn("Stop for Login");
            throw new AuthenticationException("Stop. Login First");
        }
    }
}
