const loginForm = document.querySelector("#loginForm");
const resultDiv = document.querySelector("#result");

loginForm.addEventListener("submit", async (e) => {
  e.preventDefault();

  const username = document.querySelector("#username").value.trim();
  const password = document.querySelector("#password").value;

  try {
    const res = await fetch("/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ username, password })
    });

    const { message, data } = await res.json();

    if (!res.ok) {
      showResult(`Lỗi (${res.status}): ${message}`, "red");
      return;
    }

    showResult(`${message}. Chào mừng ${data.username}`, "green");

    // ví dụ redirect
    // setTimeout(() => location.href = "/dashboard", 1000);

  } catch (err) {
    showResult("Không thể kết nối đến server.", "red");
  }
});

function showResult(message, color) {
  resultDiv.style.color = color;
  resultDiv.textContent = message;
}