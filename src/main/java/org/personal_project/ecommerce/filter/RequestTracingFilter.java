package org.personal_project.ecommerce.filter;

import jakarta.servlet.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

public class RequestTracingFilter implements Filter {
    private static final String REQUEST_ID = "requestId";
    private static final Logger logger = LoggerFactory.getLogger(RequestTracingFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        try{
            logger.info(">> BEGIN REQUEST TRACING FILTER");
            String id = UUID.randomUUID().toString().substring(0, 8);
            MDC.put(REQUEST_ID, id);
            logger.info("Get id tracing done");
            filterChain.doFilter(request, response);
        }finally {
            logger.info("<< END REQUEST TRACING FILTER");
            MDC.remove(REQUEST_ID);
        }
    }
}
