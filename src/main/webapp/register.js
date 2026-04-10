// 🔐 Escape HTML (defensive - tránh future bug)
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

// 🧠 Normalize input
function normalizePayload(payload) {
  const normalized = {};
  for (const key in payload) {
    const value = payload[key];
    normalized[key] = typeof value === "string" ? value.trim() : value;
  }
  return normalized;
}

// ✅ Validate client-side (basic nhưng đủ dùng)
function validate(payload) {
  if (!payload.username) return "Username không được để trống";
  if (!/^[a-zA-Z0-9_]{3,20}$/.test(payload.username))
    return "Username không hợp lệ";

  if (!payload.fullname) return "Họ tên không được để trống";

  if (!payload.email) return "Email không được để trống";

  if (!payload.password || payload.password.length < 8)
    return "Mật khẩu tối thiểu 8 ký tự";

  return null;
}

document
  .getElementById("registerForm")
  .addEventListener("submit", async (e) => {
    e.preventDefault();

    const form = e.target;
    const btn = document.getElementById("submitBtn");

    const formData = new FormData(form);
    let payload = Object.fromEntries(formData.entries());

    // 🔥 Normalize input
    payload = normalizePayload(payload);

    // 🔥 Validate trước khi gửi
    const validationError = validate(payload);
    if (validationError) {
      showAlert(validationError, "error");
      return;
    }

    btn.disabled = true;
    btn.textContent = "Đang xử lý...";

    try {
      const response = await fetch("/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify(payload),
      });

      const json = await response.json();

      if (response.ok) {
        // 🔐 Escape data từ server
        const safeUsername = escapeHtml(json?.data?.username);

        showAlert(`Thành công: Chào mừng ${safeUsername}!`, "success");

        form.reset();
      } else {
        const errorMsg = escapeHtml(json?.message || "Đăng ký thất bại");

        showAlert(`Lỗi (${json?.status || "?"}): ${errorMsg}`, "error");
      }
    } catch (err) {
      showAlert(
        "Không thể kết nối đến máy chủ. Vui lòng thử lại sau.",
        "error",
      );
    } finally {
      btn.disabled = false;
      btn.textContent = "Đăng ký";
    }
  });

// 🔒 Render alert an toàn tuyệt đối
function showAlert(msg, type) {
  const alertBox = document.getElementById("alert");

  // ❗ LUÔN dùng textContent
  alertBox.textContent = String(msg);

  alertBox.className = type === "success" ? "alert-success" : "alert-error";

  alertBox.style.display = "block";
}
