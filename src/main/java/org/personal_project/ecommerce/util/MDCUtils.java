package org.personal_project.ecommerce.util;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MDCUtils {
    private final static Logger logger = LoggerFactory.getLogger(MDCUtils.class);
    public static void createMDC(HttpServletRequest request, String username){
        MDC.put("username", username);
        logger.info("[UTIL]Create MDC key successful");
    }
}
