<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
    <h2>Đăng nhập</h2>

    <form id="loginForm">
      Username: <input type="text" id="username" required /><br /><br />
      Password: <input type="password" id="password" required /><br /><br />
      <button type="submit">Login</button>
    </form>

    <p id="result"></p>

    <script src="scriptLogin.js"></script>

    <%@ page isErrorPage="true" %>
    <% if (exception != null){ %>
    <pre>
        <%= exception %>
    </pre>
    <% } %>
  </body>
</html>
