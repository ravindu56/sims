package com.ec9590.sims.servlet;

import com.ec9590.sims.dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String regNo = req.getParameter("regNo");
        try {
            StudentDAO dao = new StudentDAO();
            dao.deleteStudent(regNo);
            res.sendRedirect(req.getContextPath() + "/viewStudents");
        } catch (SQLException e) {
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
}
