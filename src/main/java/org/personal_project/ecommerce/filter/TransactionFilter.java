package org.personal_project.ecommerce.filter;

import org.personal_project.ecommerce.exceptions.DatabaseException;
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
        logger.info(">> BEGIN TRANSACTION FILTER");
        chain.doFilter(request, response);
        success = true;

    } catch (Throwable e) {

        logger.info("<< EXIT TRANSACTION FILTER WITH ROLLBACK PHASE");
        if (conn != null) {
            try {
                logger.error("Transaction rollback", e);
                conn.rollback();
                throw new DatabaseException(e.getMessage());
            } catch (SQLException ex) {
                logger.error("Rollback failed", ex);
            }
        }

        ErrorHandler(e, (HttpServletRequest) request, (HttpServletResponse) response);

    } finally {
        logger.info("<< EXIT TRANSACTION FILTER WITH COMMIT PHASE");
        if (conn != null) {
            try {
                if (success) {
                    logger.info("Commit phase");
                    conn.commit();
                    logger.info("Commit successfully");
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                logger.error("Commit failed", e);
            }

            try {
                logger.info("Close connection phase");
                conn.close();
                logger.info("Close connection successfully");
            } catch (SQLException e) {
                logger.error("Close connection failed", e);
            }
        }
        logger.info("Remove connection phase");
        DBContext.removeConnection();
        logger.info("Remove connection successfully");
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
