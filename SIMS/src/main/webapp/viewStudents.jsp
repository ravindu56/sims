<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ec9590.sims.model.Student, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>All Students</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
  <h2>All Students</h2>
  <a href="addStudent.jsp" class="btn btn-green">+ Add New Student</a>

  <!-- Search Form -->
  <form action="searchStudents" method="get" style="margin:10px 0;">
    <input type="text" name="keyword" placeholder="Search by name, reg no, or field">
    <button type="submit" class="btn">Search</button>
  </form>

  <% String msg = (String) request.getAttribute("message"); %>
  <% if (msg != null) { %><p class="success"><%= msg %></p><% } %>

  <%
    List<Student> students = (List<Student>) request.getAttribute("students");
    if (students == null || students.isEmpty()) {
  %>
    <p>No student records found.</p>
  <% } else { %>
  <table>
    <thead>
      <tr>
        <th>Reg No</th><th>Name</th><th>Field</th>
        <th>Date of Birth</th><th>Contact</th><th>Email</th><th>Actions</th>
      </tr>
    </thead>
    <tbody>
    <% for (Student s : students) { %>
      <tr>
        <td><%= s.getRegNo() %></td>
        <td><%= s.getName() %></td>
        <td><%= s.getField() %></td>
        <td><%= s.getDob() %></td>
        <td><%= s.getContact() %></td>
        <td><%= s.getEmail() %></td>
        <td>
          <a href="editStudent?regNo=<%= s.getRegNo() %>" class="btn btn-sm">Edit</a>
          <a href="deleteStudent?regNo=<%= s.getRegNo() %>"
             onclick="return confirm('Delete <%= s.getName() %>?')"
             class="btn btn-sm btn-red">Delete</a>
        </td>
      </tr>
    <% } %>
    </tbody>
  </table>
  <% } %>
  <br><a href="index.jsp" class="btn">← Home</a>
</div>
</body>
</html>
