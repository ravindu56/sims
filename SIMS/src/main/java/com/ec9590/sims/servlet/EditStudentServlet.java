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
import java.time.LocalDate;

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {

    // GET - load student data into the update form
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String regNo = req.getParameter("regNo");
        try {
            StudentDAO dao = new StudentDAO();
            Student student = dao.getStudentByRegNo(regNo);
            if (student == null) {
                res.sendRedirect(req.getContextPath() + "/viewStudents");
                return;
            }
            req.setAttribute("student", student);
            req.getRequestDispatcher("/updateStudent.jsp").forward(req, res);
        } catch (SQLException e) {
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
}
