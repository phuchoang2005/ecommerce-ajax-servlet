package org.personal_project.ecommerce.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.personal_project.ecommerce.util.FilterChainTracerUtil;
import org.personal_project.ecommerce.util.FilterDebugUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;

public class AuthenticationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (isLoggedIn){
            MDC.put("user", session.getAttribute("user").toString());
            try{
                FilterDebugUtil.enter("ENTER AUTHENTICATION FILTER");
                FilterChainTracerUtil.add("AuthencationFilter");
                filterChain.doFilter(req, res);
            }finally{
                FilterDebugUtil.exit("CLOSE AUTHENTICATION FILTER");
            }
        }else{
            logger.warn("Stop for Login");
            throw new AuthenticationException("Stop. Login First");
        }
    }
}
