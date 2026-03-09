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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
    Connection conn = null;
    boolean success = false;

    try {
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);

        DBContext.setConnection(conn);

        chain.doFilter(request, response);

        success = true;

    } catch (Throwable e) {

        if (conn != null) {
            try {
                conn.rollback();
                logger.error("Transaction rollback", e);
            } catch (SQLException ex) {
                logger.error("Rollback failed", ex);
            }
        }

        ErrorHandler(e, (HttpServletRequest) request, (HttpServletResponse) response);

    } finally {

        if (conn != null) {
            try {
                if (success) {
                    conn.commit();
                }
            } catch (SQLException e) {
                logger.error("Commit failed", e);
            }

            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("Close connection failed", e);
            }
        }

        DBContext.removeConnection();
    }
}

    private void ErrorHandler(Throwable e, HttpServletRequest request, HttpServletResponse response){
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
