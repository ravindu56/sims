package com.ec9590.sims.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * VALIDATION stage:
 * Intercepts every request to protected URLs.
 * Checks if session exists and has a logged-in user attribute.
 * Redirects to login page if session is invalid or missing.
 */
@WebFilter(urlPatterns = {
    "/viewStudents",
    "/addStudent",
    "/editStudent",
    "/updateStudent",
    "/deleteStudent",
    "/searchStudents",
    "/addStudent.jsp",
    "/updateStudent.jsp",
    "/viewStudents.jsp",
    "/index.jsp"
})
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest  req = (HttpServletRequest)  request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Get existing session — do NOT create a new one here
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null)
                          && (session.getAttribute("loggedInUser") != null);

        if (isLoggedIn) {
            // Valid session — continue to the requested resource
            chain.doFilter(request, response);
        } else {
            // No valid session — redirect to login
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
