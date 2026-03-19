<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Add Student</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
  <h2>Add New Student</h2>

  <% String error = (String) request.getAttribute("error"); %>
  <% if (error != null) { %>
    <p class="error"><%= error %></p>
  <% } %>

  <form action="addStudent" method="post">
    <label>Registration No:</label>
    <input type="text" name="regNo" required placeholder="e.g. 2021/E/001"><br>

    <label>Full Name:</label>
    <input type="text" name="name" required placeholder="e.g. Amal Perera"><br>

    <label>Field of Study:</label>
    <select name="field" required>
      <option value="">-- Select Field --</option>
      <option>Computer Engineering</option>
      <option>Electrical Engineering</option>
      <option>Civil Engineering</option>
      <option>Mechanical Engineering</option>
    </select><br>

    <label>Date of Birth:</label>
    <input type="date" name="dob" required><br>

    <label>Contact No:</label>
    <input type="text" name="contact" placeholder="e.g. 0771234567"><br>

    <label>Email:</label>
    <input type="email" name="email" placeholder="e.g. student@example.com"><br>

    <button type="submit" class="btn btn-green">Add Student</button>
    <a href="viewStudents" class="btn">Cancel</a>
  </form>
</div>
</body>
</html>
