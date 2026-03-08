document
  .getElementById("registerForm")
  .addEventListener("submit", async (e) => {
    e.preventDefault();

    const form = e.target;
    const btn = document.getElementById("submitBtn");
    const alertBox = document.getElementById("alert");

    // 1. Thu thập dữ liệu (Khớp với RegisterRequest)
    const formData = new FormData(form);
    const payload = Object.fromEntries(formData.entries());

    // UI Reset
    alertBox.style.display = "none";
    btn.disabled = true;
    btn.textContent = "Đang xử lý...";

    try {
      // 2. Gửi AJAX (Fetch API)
      const response = await fetch("/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });

      const json = await response.json();

      if (response.ok) {
        // Xử lý RegisterResponse (200 hoặc 201)
        showAlert(
          `Thành công: ${json.message}. Chào mừng ${json.data.username}!`,
          "success",
        );
        form.reset();
        // Có thể redirect sau 2s: setTimeout(() => window.location.href = '/login', 2000);
      } else {
        // Xử lý ErrorResponse
        // Theo schema: data.message, data.status, data.error
        const errorMsg = json.message || "Đăng ký thất bại";
        showAlert(`Lỗi (${json.status}): ${errorMsg}`, "error");

        console.error(`Error at ${json.path}: ${json.error}`);
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

function showAlert(msg, type) {
  const alertBox = document.getElementById("alert");
  alertBox.textContent = msg;
  alertBox.className = type === "success" ? "alert-success" : "alert-error";
  alertBox.style.display = "block";
}
