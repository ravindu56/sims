package com.ec9590.sims.dao;

import com.ec9590.sims.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public boolean addStudent(Student s) throws SQLException {
        String sql = "INSERT INTO students (reg_no, name, field, dob, contact, email) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getRegNo());
            ps.setString(2, s.getName());
            ps.setString(3, s.getField());
            ps.setDate(4, Date.valueOf(s.getDob()));
            ps.setString(5, s.getContact());
            ps.setString(6, s.getEmail());

            return ps.executeUpdate() > 0;
        }
    }


    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY reg_no";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }


    public Student getStudentByRegNo(String regNo) throws SQLException {
        String sql = "SELECT * FROM students WHERE reg_no = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, regNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }


    public boolean updateStudent(Student s) throws SQLException {
        String sql = "UPDATE students SET name=?, field=?, dob=?, contact=?, email=? " +
                     "WHERE reg_no=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setString(2, s.getField());
            ps.setDate(3, Date.valueOf(s.getDob()));
            ps.setString(4, s.getContact());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getRegNo());

            return ps.executeUpdate() > 0;
        }
    }


    public boolean deleteStudent(String regNo) throws SQLException {
        String sql = "DELETE FROM students WHERE reg_no = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, regNo);
            return ps.executeUpdate() > 0;
        }
    }


    public List<Student> searchStudents(String keyword) throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ? OR reg_no LIKE ? OR field LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            ps.setString(3, pattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setId(rs.getInt("id"));
        s.setRegNo(rs.getString("reg_no"));
        s.setName(rs.getString("name"));
        s.setField(rs.getString("field"));
        s.setDob(rs.getDate("dob").toLocalDate());
        s.setContact(rs.getString("contact"));
        s.setEmail(rs.getString("email"));
        return s;
    }
}
