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

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {

    // POST - save the updated student record
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

        Student s = new Student();
        s.setRegNo(regNo);
        s.setName(name);
        s.setField(field);
        s.setDob(LocalDate.parse(dobStr));
        s.setContact(contact);
        s.setEmail(email);

        try {
            StudentDAO dao = new StudentDAO();
            dao.updateStudent(s);
            res.sendRedirect(req.getContextPath() + "/viewStudents");
        } catch (SQLException e) {
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.setAttribute("student", s);
            req.getRequestDispatcher("/updateStudent.jsp").forward(req, res);
        }
    }
}
