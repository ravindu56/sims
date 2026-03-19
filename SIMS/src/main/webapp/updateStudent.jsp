<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ec9590.sims.model.Student" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Update Student</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
  <h2>Update Student Record</h2>
  <%
    Student s = (Student) request.getAttribute("student");
    if (s == null) { response.sendRedirect("viewStudents"); return; }
  %>
  <form action="updateStudent" method="post">
    <input type="hidden" name="regNo" value="<%= s.getRegNo() %>">

    <label>Registration No:</label>
    <input type="text" value="<%= s.getRegNo() %>" disabled><br>

    <label>Full Name:</label>
    <input type="text" name="name" value="<%= s.getName() %>" required><br>

    <label>Field of Study:</label>
    <select name="field" required>
      <% String[] fields = {"Computer Engineering","Electrical Engineering",
                            "Civil Engineering","Mechanical Engineering"};
         for (String f : fields) { %>
        <option value="<%= f %>" <%= f.equals(s.getField()) ? "selected" : "" %>><%= f %></option>
      <% } %>
    </select><br>

    <label>Date of Birth:</label>
    <input type="date" name="dob" value="<%= s.getDob() %>" required><br>

    <label>Contact No:</label>
    <input type="text" name="contact" value="<%= s.getContact() %>"><br>

    <label>Email:</label>
    <input type="email" name="email" value="<%= s.getEmail() %>"><br>

    <button type="submit" class="btn btn-green">Update</button>
    <a href="viewStudents" class="btn">Cancel</a>
  </form>
</div>
</body>
</html>
