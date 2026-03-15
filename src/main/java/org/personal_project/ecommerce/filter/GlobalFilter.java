package org.personal_project.ecommerce.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.personal_project.ecommerce.util.FilterChainTracerUtil;
import org.personal_project.ecommerce.util.FilterDebugUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
public class GlobalFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(GlobalFilter.class);
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException{

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        logger.debug("Request: {} and Response: {}", req.getMethod(), req.getRequestURI());
        try{
            FilterDebugUtil.enter("BEGIN GLOBAL FILTER");
            FilterChainTracerUtil.add("GlobalFilter");
            filterChain.doFilter(request, response);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        finally{
            FilterDebugUtil.exit("END GLOBAL FILTER");
        }
    }
}
