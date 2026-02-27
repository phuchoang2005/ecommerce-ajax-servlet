package org.personal_project.ecommerce.filter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.personal_project.ecommerce.dto.ApiErrorResponse;
import org.personal_project.ecommerce.exceptions.*;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class GlobalExceptionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        try{
            filterChain.doFilter(request, response);
        }catch(Exception e){
            handleException(e, req, res);
        }
    }
    static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException{
        ApiErrorResponse apiErrorResponse = switch(e){
            case ValidationException ve -> new ApiErrorResponse(ve.getStatusCode(), ve.getError(),ve.getMessage(), request.getRequestURI());
            case AuthenticationException ae -> new ApiErrorResponse(ae.getStatusCode(), ae.getError(),ae.getMessage(), request.getRequestURI());
            case JsonSyntaxException je -> new ApiErrorResponse(HttpServletResponse.SC_BAD_REQUEST,"JSON invalid", je.getMessage(), request.getRequestURI());
            case QueryException qe -> new ApiErrorResponse(qe.getStatusCode(), qe.getError(), qe.getMessage(), request.getRequestURI());
            case InputOutputException ioe -> new ApiErrorResponse(ioe.getStatusCode(), ioe.getError(), ioe.getMessage(), request.getRequestURI());
            default -> new ApiErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error", "Undefined", request.getRequestURI());
        };

        response.setStatus(apiErrorResponse.getStatus());
        response.getWriter().write(new Gson().toJson(apiErrorResponse));
    }
}

