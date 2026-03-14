package org.personal_project.ecommerce.filter;

import org.personal_project.ecommerce.exceptions.DatabaseException;
import org.personal_project.ecommerce.exceptions.InputOutputException;
import org.personal_project.ecommerce.util.DBConnectionutil;
import org.personal_project.ecommerce.util.DBContextUtil;
import org.personal_project.ecommerce.util.FilterChainTracerUtil;
import org.personal_project.ecommerce.util.FilterDebugUtil;

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
        conn = DBConnectionutil.getConnection();
        conn.setAutoCommit(false);

        DBContextUtil.setConnection(conn);
        
        FilterDebugUtil.enter("ENTER TRANSACTION FILTER");
        FilterChainTracerUtil.add("TransactionFilter");
        chain.doFilter(request, response);

        success = true;

    } catch (Throwable e) {
        FilterDebugUtil.exit("EXIT TRANSACTION FILTER WITH ROLLBACK PHASE");

        if (conn != null) {
            try {
                logger.info("Transaction Rollback Phase");
                conn.rollback();
                logger.info("Transaction Rolled back");
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                throw new DatabaseException("Rollback failed");
            }
        }

        ErrorHandler(e, (HttpServletRequest) request, (HttpServletResponse) response);

    } finally {
        FilterDebugUtil.exit("EXIT TRNSACTION FILTER WITH COMMIT PHASE");
        if (conn != null) {
            try {
                if (success) {
                    logger.info("Commit Phase");
                    conn.commit();
                    conn.setAutoCommit(true);
                    logger.info("Commit Done");
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DatabaseException("Commit failed");
            }

            try {
                logger.info("Connection Phase");
                conn.close();
                logger.info("Connection Closed");
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DatabaseException("Close connection failed");
            }
        }
        DBContextUtil.removeConnection();
    }
}

    private void ErrorHandler(Throwable e, HttpServletRequest request, HttpServletResponse response){
        try{
            if (!response.isCommitted()){
                GlobalExceptionFilter.handleException(e, request, response);
            }else{
                logger.error("Response already committed. Can't send JSON file");
                throw new InputOutputException("Response already committed. Can't send JSON file");
            }
        }catch (IOException eIO){
            logger.error(eIO.getMessage());
            throw new DatabaseException("IO Error at Database connection");
        }
    }
}
