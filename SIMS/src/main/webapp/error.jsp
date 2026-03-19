<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head><title>Error</title><link rel="stylesheet" href="css/style.css"></head>
<body>
<div class="container">
  <h2>Something went wrong</h2>
  <p><%= exception != null ? exception.getMessage() : "An unexpected error occurred." %></p>
  <a href="index.jsp" class="btn">← Go Home</a>
</div>
</body>
</html>
