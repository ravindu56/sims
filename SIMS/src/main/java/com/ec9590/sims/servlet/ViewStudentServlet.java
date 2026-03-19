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

@WebServlet("/viewStudents")
public class ViewStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            StudentDAO dao = new StudentDAO();
            List<Student> students = dao.getAllStudents();
            req.setAttribute("students", students);
            req.getRequestDispatcher("/viewStudents.jsp").forward(req, res);
        } catch (SQLException e) {
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
}
