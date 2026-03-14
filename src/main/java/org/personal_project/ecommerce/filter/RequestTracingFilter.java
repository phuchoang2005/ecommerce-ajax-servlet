package org.personal_project.ecommerce.filter;

import jakarta.servlet.*;

import org.slf4j.LoggerFactory;
import org.personal_project.ecommerce.util.FilterChainTracerUtil;
import org.personal_project.ecommerce.util.FilterDebugUtil;
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
            String id = UUID.randomUUID().toString().substring(0, 8);
            MDC.put(REQUEST_ID, id);
            logger.info("Get id tracing done");
            FilterDebugUtil.enter("BEGIN REQUEST TRACING FILTER");
            FilterChainTracerUtil.add("RequestTracingFilter");
            filterChain.doFilter(request, response);
        }catch(Exception e){
            FilterDebugUtil.exit("END REQUEST TRACING FILTER WITH EXCEPTION");
            logger.error(e.getMessage());
            throw new RuntimeException("Error when create session tracing id");
        }
        finally {
            FilterDebugUtil.exit("END REQUEST TRACING FILTER WITH HAPPY");
            MDC.remove(REQUEST_ID);

        }
    }
}
