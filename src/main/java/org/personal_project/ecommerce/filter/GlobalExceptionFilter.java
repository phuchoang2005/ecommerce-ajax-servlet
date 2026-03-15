package org.personal_project.ecommerce.filter;

import com.google.gson.Gson;
import org.personal_project.ecommerce.api.ApiExceptionResponse;
import org.personal_project.ecommerce.enums.HttpStatusEnum;
import org.personal_project.ecommerce.exceptions.BaseException;
import org.personal_project.ecommerce.util.FilterChainTracerUtil;
import org.personal_project.ecommerce.util.FilterDebugUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GlobalExceptionFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        try{
            FilterChainTracerUtil.clear();
            FilterChainTracerUtil.add("GlobalExceptionFilter");
            FilterDebugUtil.enter("BEGIN GLOBAL EXCEPTION FILTER");
            filterChain.doFilter(request, response);
        }catch(Exception e){
            logger.error(e.getMessage());
            FilterDebugUtil.exit("CLOSE GLOBAL EXCEPTION FILTER WITH EXCEPTION");
            handleException(e, req, res);
        }finally{
            FilterDebugUtil.exit("CLOSE GLOBAL EXCEPTION FILTER WITH HAPPY");
            logger.info("[FITLER-CHAIN]: {}", FilterChainTracerUtil.getChain());
            FilterChainTracerUtil.clear();;
        }
    }
    static void handleException(Throwable e, HttpServletRequest request, HttpServletResponse response) throws IOException{
        ApiExceptionResponse apiExceptionResponse = switch(e){
            case BaseException exception -> new ApiExceptionResponse(exception.getStatusCode(), exception.getError(), exception.getMessage(), request.getRequestURI());
            default -> new ApiExceptionResponse(HttpStatusEnum.INTERNAL_ERROR.code(),HttpStatusEnum.INTERNAL_ERROR.message(), e.getMessage(), request.getRequestURI());
        };
        response.setStatus(apiExceptionResponse.getStatus());
        response.getWriter().write(new Gson().toJson(apiExceptionResponse));
    }
}

