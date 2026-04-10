package org.personal_project.ecommerce.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-Frame-Options", "DENY");
        response.setHeader("X-XSS-Protection", "1; mode=block");

        // 🔥 CSP - chống XSS mạnh nhất
        response.setHeader("Content-Security-Policy",
                "default-src 'self'; script-src 'self'; object-src 'none';");

        chain.doFilter(req, res);
    }
}