package org.personal_project.ecommerce.filter;

import jakarta.servlet.annotation.WebFilter;
import org.personal_project.ecommerce.util.DBConnection;
import org.personal_project.ecommerce.util.DBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
@WebFilter("/*")
public class TransactionFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(TransactionFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain){
        Connection conn = null;
        try{
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            DBContext.setConnection(conn);

            filterChain.doFilter(request, response);

            conn.commit();
        }catch(Exception e){
            if (conn != null){
                try{
                    conn.rollback();
                }catch(Exception ex){
                    logger.error("Error when rollback", ex);
                }
            }
            logger.error("Error create connection", e);
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            ErrorHandler(e, req, res);
        }finally {
            DBContext.removeConnection();
            if (conn != null){
                try{
                    conn.close();
                }catch(SQLException e){
                    logger.error("Error when close connection", e);
                }
            }
        }
    }

    private void ErrorHandler(Exception e, HttpServletRequest request, HttpServletResponse response){
        try{
            if (!response.isCommitted()){
                GlobalExceptionFilter.handleException(e, request, response);
            }else{
                logger.error("Response already committed. Can't send JSON file");
            }
        }catch (IOException eIO){
            logger.error("IO Error at Database connection", eIO);
        }
    }
}
