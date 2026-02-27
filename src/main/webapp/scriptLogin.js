document.getElementById("loginForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  const resultDiv = document.getElementById("result");

  // Gửi JSON thay vì Form-urlencoded
  fetch("/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ username, password }),
  })
    .then(async (res) => {
      const data = await res.json();

      if (res.ok) {
        // Status 200-299
        resultDiv.style.color = "green";
        resultDiv.innerHTML = `${data.message}`;
        // Redirect sau 1s chẳng hạn: window.location.href = "/dashboard";
      } else {
        resultDiv.style.color = "red";
        resultDiv.innerHTML = `Lỗi (${res.status}): ${data.message}`;
      }
    })
    .catch((err) => {
      resultDiv.innerHTML = "️Không thể kết nối đến server.";
    });
});
