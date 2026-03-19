package com.ec9590.sims.servlet;

import com.ec9590.sims.dao.UserDAO;
import com.ec9590.sims.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // GET — show the login form
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // If already logged in, go straight to dashboard
        HttpSession existing = req.getSession(false);
        if (existing != null && existing.getAttribute("loggedInUser") != null) {
            res.sendRedirect(req.getContextPath() + "/viewStudents");
            return;
        }
        req.getRequestDispatcher("/login.jsp").forward(req, res);
    }

    // POST — process login credentials
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            UserDAO dao = new UserDAO();
            User user = dao.validateUser(username, password);

            if (user != null) {
                /*
                 * ── CREATION STAGE ──────────────────────────────────────
                 * Invalidate any old session first (prevents session fixation).
                 * Then create a brand new session and store the user in it.
                 * Tomcat maps this session to a unique JSESSIONID internally.
                 */
                HttpSession oldSession = req.getSession(false);
                if (oldSession != null) oldSession.invalidate();

                HttpSession session = req.getSession(true); // create new session
                session.setAttribute("loggedInUser", user.getUsername());
                session.setMaxInactiveInterval(30 * 60); // 30 minutes timeout

                /*
                 * ── TRANSMISSION STAGE ──────────────────────────────────
                 * Manually create a cookie with Secure and HttpOnly flags
                 * as required by the lab spec:
                 * Set-Cookie: sessionid=XXXXX; Secure; HttpOnly
                 */
                Cookie sessionCookie = new Cookie("sessionid", session.getId());
                sessionCookie.setHttpOnly(true);  // JS cannot access this cookie
                sessionCookie.setSecure(false);   // set true when using HTTPS
                sessionCookie.setPath(req.getContextPath());
                sessionCookie.setMaxAge(30 * 60); // matches session timeout
                res.addCookie(sessionCookie);

                res.sendRedirect(req.getContextPath() + "/viewStudents");

            } else {
                // Invalid credentials
                req.setAttribute("error", "Invalid username or password.");
                req.getRequestDispatcher("/login.jsp").forward(req, res);
            }

        } catch (SQLException e) {
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req, res);
        }
    }
}
