package org.personal_project.ecommerce.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionUtils {
    private final static Logger logger = LoggerFactory.getLogger(SessionUtils.class);

    public static void refreshSession(HttpServletRequest request, String username){
        logger.info("Create new session for username {}", username);
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null){
            oldSession.invalidate();
        }
        HttpSession newSession = request.getSession(true);
        newSession.setAttribute("user", username);
        logger.info("[UTIL]Create new session for username {} successfully", username);
    }
}
