package com.ec9590.sims.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        /*
         * ── TERMINATION STAGE ───────────────────────────────────────
         * 1. Invalidate the server-side session (destroys all attributes).
         * 2. Clear the cookie on the browser side by setting MaxAge to 0.
         */

        // Step 1: Destroy the server-side session
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Step 2: Clear the custom sessionid cookie from the browser
        Cookie killCookie = new Cookie("sessionid", "");
        killCookie.setMaxAge(0);  // tells browser to delete it immediately
        killCookie.setPath(req.getContextPath());
        res.addCookie(killCookie);

        // Step 3: Also clear the JSESSIONID cookie
        Cookie killJSession = new Cookie("JSESSIONID", "");
        killJSession.setMaxAge(0);
        killJSession.setPath(req.getContextPath());
        res.addCookie(killJSession);

        res.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}
