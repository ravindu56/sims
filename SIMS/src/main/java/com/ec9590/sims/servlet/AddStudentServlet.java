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

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {

    // GET - show the form
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("/addStudent.jsp").forward(req, res);
    }

    // POST - process form submission
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String regNo   = req.getParameter("regNo").trim();
        String name    = req.getParameter("name").trim();
        String field   = req.getParameter("field").trim();
        String dobStr  = req.getParameter("dob");
        String contact = req.getParameter("contact").trim();
        String email   = req.getParameter("email").trim();

        // Basic validation
        if (regNo.isEmpty() || name.isEmpty() || field.isEmpty() || dobStr.isEmpty()) {
            req.setAttribute("error", "Registration No, Name, Field and DOB are required.");
            req.getRequestDispatcher("/addStudent.jsp").forward(req, res);
            return;
        }

        Student s = new Student();
        s.setRegNo(regNo);
        s.setName(name);
        s.setField(field);
        s.setDob(LocalDate.parse(dobStr));
        s.setContact(contact);
        s.setEmail(email);

        try {
            StudentDAO dao = new StudentDAO();
            boolean success = dao.addStudent(s);
            if (success) {
                res.sendRedirect(req.getContextPath() + "/viewStudents");
            } else {
                req.setAttribute("error", "Failed to add student. Please try again.");
                req.getRequestDispatcher("/addStudent.jsp").forward(req, res);
            }
        } catch (SQLException e) {
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.getRequestDispatcher("/addStudent.jsp").forward(req, res);
        }
    }
}
