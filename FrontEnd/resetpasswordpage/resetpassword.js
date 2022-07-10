const email = document.getElementById("emailInput");
const submitReset = document.getElementById("submitButton");
submitReset.disabled = true;

const specialChar2 = /[ `^*()+=\[\]{};':"\\|,<>\/~]/;
const invalidIcon = document.querySelectorAll("[id='invalid-icon']");
let infoIcon = document.querySelectorAll(".info-icon");
const url = "http://localhost:8080";
let validateCounter = 0;

const div = document.getElementById("errorMessageGoesHere");
div.textContent = "";

async function resetPasscode() {
  let response = await fetch("http://localhost:8080/user/reset-password", {
    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      email: email.value,

    }),
  });

  if (response.status === 200) {
    let body = await response.json();
    //  Storing information for later
    localStorage.setItem("userPassword", JSON.stringify(body));
    window.location.href = "../profilepage/login.html";
  } else {
    div.textContent = "Invalid Email";
  }
}

let jsonEmailObject = {
  email: "",
};

//  ----------------------------  Validation for inputs ----------------------------
email.addEventListener("focusin", resetEmail());
function resetPasscode() {
  passcodes.addEventListener("focusout", function () {
    if (email.value == "") {
      invalidIcon[1].style.display = "";
      infoIcon[1].style.display = "block";
      invalidMessage[1].textContent = "Password is incorrect or missing";
    } else if (specialChar2.test(email.value)) {
      invalidIcon[1].style.display = "";
      infoIcon[1].style.display = "block";
      invalidMessage[1].textContent =
        'Cannot contain spaces or special characters: `^*()+=[]{}"<>~|;:';
    } else {
      validateCounter += 1;
      invalidIcon[1].style.display = "none";
      invalidMessage[1].textContent = "";
      jsonEmailObject.email = email.value;
      if (validateCounter > 1) {
        submitReset.disabled = false;
      }
    }
  });
}
submitLogin.addEventListener("click", resetPasscode);
