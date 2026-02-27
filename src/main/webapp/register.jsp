<%-- Created by IntelliJ IDEA. User: phuchoang Date: 27/2/26 Time: 21:58 To
change this template use File | Settings | File Templates. --%> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Đăng ký hệ thống</title>
    <style>
      :root {
        --primary: #2563eb;
        --error: #dc2626;
        --success: #16a34a;
      }
      body {
        font-family: "Segoe UI", sans-serif;
        background: #f3f4f6;
        display: flex;
        justify-content: center;
        padding: 20px;
      }
      .card {
        background: white;
        padding: 2rem;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 450px;
      }
      h2 {
        margin-top: 0;
        color: #1f2937;
        text-align: center;
      }

      .form-group {
        margin-bottom: 1rem;
      }
      label {
        display: block;
        font-size: 0.875rem;
        font-weight: 500;
        margin-bottom: 0.25rem;
      }
      input {
        width: 100%;
        padding: 0.625rem;
        border: 1px solid #d1d5db;
        border-radius: 0.375rem;
        transition: border 0.2s;
      }
      input:focus {
        outline: none;
        border-color: var(--primary);
        ring: 2px var(--primary);
      }

      .error-text {
        color: var(--error);
        font-size: 0.75rem;
        margin-top: 0.25rem;
        display: none;
      }
      #alert {
        padding: 10px;
        border-radius: 4px;
        margin-bottom: 1rem;
        display: none;
        font-size: 0.875rem;
      }
      .alert-error {
        background: #fee2e2;
        color: var(--error);
        border: 1px solid #fecaca;
      }
      .alert-success {
        background: #dcfce7;
        color: var(--success);
        border: 1px solid #bbf7d0;
      }

      button {
        width: 100%;
        background: var(--primary);
        color: white;
        padding: 0.75rem;
        border: none;
        border-radius: 0.375rem;
        font-weight: 600;
        cursor: pointer;
      }
      button:disabled {
        opacity: 0.7;
        cursor: not-allowed;
      }
    </style>
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
            name="fullName"
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
    <Script src="register.js"></Script>
  </body>
</html>
