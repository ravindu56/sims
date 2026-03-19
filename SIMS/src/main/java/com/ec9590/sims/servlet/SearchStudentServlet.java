package com.ec9590.sims.servlet;

import com.ec9590.sims.dao.StudentDAO;
import com.ec9590.sims.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/searchStudents")
public class SearchStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        if (keyword == null || keyword.trim().isEmpty()) {
            res.sendRedirect(req.getContextPath() + "/viewStudents");
            return;
        }
        try {
            StudentDAO dao = new StudentDAO();
            List<Student> students = dao.searchStudents(keyword.trim());
            req.setAttribute("students", students);
            req.setAttribute("keyword", keyword);
            req.getRequestDispatcher("/viewStudents.jsp").forward(req, res);
        } catch (SQLException e) {
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
}
