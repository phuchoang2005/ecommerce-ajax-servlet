package com.personal_project.ecommerce.security;

import jakarta.servlet.*;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

public class RequestTracingFilter implements Filter {
    private static final String REQUEST_ID = "requestId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        try{
            String id = UUID.randomUUID().toString().substring(0, 8);
            MDC.put(REQUEST_ID, id);

            filterChain.doFilter(request, response);
        }finally {
            MDC.remove(REQUEST_ID);
        }
    }
}
