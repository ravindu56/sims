<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ec9590.sims.model.Student, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>SIMS - All Students</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">

  <!-- ── PERSISTENCE: session attribute shown on every protected page ── -->
  <div style="display:flex; justify-content:space-between; align-items:center;
              background:#f8f9fa; padding:10px 15px; border-radius:6px;
              margin-bottom:20px; border-left:4px solid #27ae60;">
    <span>
      Logged in as: <strong><%= session.getAttribute("loggedInUser") %></strong>
    </span>
    <a href="<%= request.getContextPath() %>/logout" class="btn btn-red btn-sm">
      Logout
    </a>
  </div>

  <h2>All Students</h2>

  <!-- Action buttons -->
  <div style="margin-bottom:10px;">
    <a href="<%= request.getContextPath() %>/addStudent" class="btn btn-green">
      + Add New Student
    </a>
  </div>

  <!-- Search Form -->
  <form action="<%= request.getContextPath() %>/searchStudents" method="get"
        style="margin:10px 0; display:flex; gap:8px;">
    <input type="text" name="keyword"
           placeholder="Search by name, reg no, or field"
           style="flex:1;">
    <button type="submit" class="btn">Search</button>
    <a href="<%= request.getContextPath() %>/viewStudents" class="btn">
      Clear
    </a>
  </form>

  <!-- Success / Error Messages -->
  <%
    String msg   = (String) request.getAttribute("message");
    String error = (String) request.getAttribute("error");
    String keyword = (String) request.getAttribute("keyword");
  %>
  <% if (msg   != null) { %><p class="success"><%= msg %></p><% } %>
  <% if (error != null) { %><p class="error"><%= error %></p>  <% } %>
  <% if (keyword != null) { %>
    <p style="color:#555; font-size:13px;">
      Search results for: <strong>"<%= keyword %>"</strong>
    </p>
  <% } %>

  <!-- Student Table -->
  <%
    List<Student> students = (List<Student>) request.getAttribute("students");
    if (students == null || students.isEmpty()) {
  %>
    <p style="color:#888; margin-top:20px;">No student records found.</p>
  <% } else { %>
    <p style="font-size:13px; color:#666;">
      Total records: <strong><%= students.size() %></strong>
    </p>
    <table>
      <thead>
        <tr>
          <th>#</th>
          <th>Reg No</th>
          <th>Name</th>
          <th>Field</th>
          <th>Date of Birth</th>
          <th>Contact</th>
          <th>Email</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
      <%
        int count = 1;
        for (Student s : students) {
      %>
        <tr>
          <td><%= count++ %></td>
          <td><%= s.getRegNo() %></td>
          <td><%= s.getName() %></td>
          <td><%= s.getField() %></td>
          <td><%= s.getDob() %></td>
          <td><%= s.getContact() != null ? s.getContact() : "-" %></td>
          <td><%= s.getEmail()   != null ? s.getEmail()   : "-" %></td>
          <td>
            <a href="<%= request.getContextPath() %>/editStudent?regNo=<%= s.getRegNo() %>"
               class="btn btn-sm">Edit</a>
            <a href="<%= request.getContextPath() %>/deleteStudent?regNo=<%= s.getRegNo() %>"
               onclick="return confirm('Are you sure you want to delete <%= s.getName() %>?')"
               class="btn btn-sm btn-red">Delete</a>
          </td>
        </tr>
      <% } %>
      </tbody>
    </table>
  <% } %>

  <br>
  <a href="<%= request.getContextPath() %>/index.jsp" class="btn">← Home</a>

</div>
</body>
</html>
