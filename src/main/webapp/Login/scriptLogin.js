document
        .getElementById("loginForm")
        .addEventListener("submit", function (e) {
          e.preventDefault();

          let username = document.getElementById("username").value;
          let password = document.getElementById("password").value;

          fetch("/general/login", {
            method: "POST",
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
            },
            body: `username=${username}&password=${password}`
          })
            .then((res) => res.text())
            .then((data) => {
              document.getElementById("result").innerHTML = data;
            });
        });