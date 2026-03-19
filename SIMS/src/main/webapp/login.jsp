<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>SIMS - Login</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container" style="max-width:400px; margin-top:80px;">
  <h2>SIMS Login</h2>
  <p style="color:#666; font-size:13px;">EC9590 - Network Application Design</p>

  <% String error = (String) request.getAttribute("error"); %>
  <% if (error != null) { %>
    <p class="error"><%= error %></p>
  <% } %>

  <form action="login" method="post">
    <label>Username:</label>
    <input type="text" name="username" required autofocus
           placeholder="Enter username"><br>

    <label>Password:</label>
    <input type="password" name="password" required
           placeholder="Enter password"><br>

    <button type="submit" class="btn btn-green" style="width:100%; margin-top:15px;">
      Login
    </button>
  </form>
</div>
</body>
</html>
