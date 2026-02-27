package org.personal_project.ecommerce.util;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

public class MDCUtils {
    public static void createMDC(HttpServletRequest request, String username){
        MDC.put("username", username);
    }
}
