// login.js

// 🔐 Escape HTML để tránh XSS
function escapeHtml(input) {
  if (!input) return "";
  return input
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&#x27;")
    .replace(/\//g, "&#x2F;");
}

// 🔒 Render message an toàn
function showMessage(msg) {
  const result = document.getElementById("result");

  // luôn dùng textContent (không dùng innerHTML)
  result.textContent = String(msg);
}

document.getElementById("loginForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value;

  // payload gửi server
  const payload = {
    username: username,
    password: password,
  };

  try {
    const response = await fetch("/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(payload),
    });

    const json = await response.json();

    if (response.ok) {
      // 🔐 Escape dữ liệu server trả về
      const safeUser = escapeHtml(json?.data?.username);

      showMessage(`Đăng nhập thành công. Xin chào ${safeUser}`);
    } else {
      const errorMsg = escapeHtml(json?.message || "Đăng nhập thất bại");

      showMessage(`Lỗi: ${errorMsg}`);
    }
  } catch (error) {
    showMessage("Không thể kết nối tới máy chủ.");
    console.error(error);
  }
});
