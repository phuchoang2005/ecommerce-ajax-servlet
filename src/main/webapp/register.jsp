<%-- Created by IntelliJ IDEA. User: phuchoang Date: 27/2/26 Time: 21:58 To
change this template use File | Settings | File Templates. --%> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Đăng ký hệ thống</title>
  </head>
  <body>
    <div class="card">
      <h2>Tạo tài khoản</h2>
      <div id="alert"></div>

      <form id="registerForm">
        <div class="form-group">
          <label>Username *</label>
          <input
            type="text"
            name="username"
            required
            placeholder="Ví dụ: bosshp2000"
          />
        </div>

        <div class="form-group">
          <label>Họ và tên *</label>
          <input
            type="text"
            name="fullname"
            required
            placeholder="Nguyễn Văn A"
          />
        </div>

        <div class="form-group">
          <label>Email *</label>
          <input
            type="email"
            name="email"
            required
            placeholder="nguyenvana@example.com"
          />
        </div>

        <div class="form-group">
          <label>Mật khẩu *</label>
          <input
            type="password"
            name="password"
            required
            minlength="8"
            placeholder="••••••••"
          />
        </div>

        <div class="form-group">
          <label>Số điện thoại</label>
          <input type="tel" name="phone" placeholder="0987654321" />
        </div>

        <div class="form-group">
          <label>Địa chỉ</label>
          <input type="text" name="address" placeholder="Hà Nội, Việt Nam" />
        </div>

        <button type="submit" id="submitBtn">Đăng ký</button>
      </form>
    </div>
    <script src="register.js"></script>
  </body>
</html>
