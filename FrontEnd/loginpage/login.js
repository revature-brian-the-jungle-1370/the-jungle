const usernames = document.getElementById("usernameInput");
const passcodes = document.getElementById("passcodeInput");
const submitLogin = document.getElementById("submitLogin");
submitLogin.disabled = true;
let loginStatus = false;
const specialChar2 = /[ `^*()+=\[\]{};':"\\|,<>\/~]/;
const invalidIcon = document.querySelectorAll("[id='invalid-icon']");
let invalidMessage = document.querySelectorAll("[id='signup-invalid-message']");
let infoIcon = document.querySelectorAll(".info-icon");
// const url = "http://ec2-52-200-53-62.compute-1.amazonaws.com:8080";
// const frontendUrl="http://dans-code.net.s3-website-us-east-1.amazonaws.com";
const url = "http://127.0.0.1:8080";
const frontendUrl="http://127.0.0.1:5500";
let validateCounter = 0;

const div = document.getElementById("errorMessageGoesHere");
div.textContent = "";

async function login() {

  let response = await fetch(url + "/user/login", {

    method: "POST",
    mode: "cors",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      username: usernames.value,
      passcode: passcodes.value,
    }),
  });
  if (response.status == 200) {
    let body = await response.json();
    //  Storing information for later
    localStorage.setItem("userInfo", JSON.stringify(body));
    localStorage.setItem("userId", body.userId);
    window.location.href = "../profilepage/profile-page.html?user_id="+body.userId
  } else {
    div.textContent = "Incorrect Username or Password";
  }
}

let jsonLoginObject = {
  username: "",
  passcode: "",
};

//  ----------------------------  Validation for inputs ----------------------------
usernames.addEventListener("focusin", loginUsername());
function loginUsername() {
  usernames.addEventListener("focusout", function () {
    if (usernames.value == "") {
      invalidIcon[0].style.display = "";
      infoIcon[0].style.display = "block";
      invalidMessage[0].textContent = "Username is incorrect or missing";
    } else if (specialChar2.test(usernames.value)) {
      invalidIcon[0].style.display = "";
      infoIcon[0].style.display = "block";
      invalidMessage[0].textContent =
        'Cannot contain spaces or special characters: `^*()+=[]{}"<>~|;:';
    } else {
      validateCounter += 1;
      invalidIcon[0].style.display = "none";
      invalidMessage[0].textContent = "";
      jsonLoginObject.username = usernames.value;
    }
  });
}

passcodes.addEventListener("focusin", loginPasscode());
function loginPasscode() {
  passcodes.addEventListener("focusout", function () {
    if (passcodes.value == "") {
      invalidIcon[1].style.display = "";
      infoIcon[1].style.display = "block";
      invalidMessage[1].textContent = "Password is incorrect or missing";
    } else if (specialChar2.test(passcodes.value)) {
      invalidIcon[1].style.display = "";
      infoIcon[1].style.display = "block";
      invalidMessage[1].textContent =
        'Cannot contain spaces or special characters: `^*()+=[]{}"<>~|;:';
    } else {
      validateCounter += 1;
      invalidIcon[1].style.display = "none";
      invalidMessage[1].textContent = "";
      jsonLoginObject.passcode = passcodes.value;
      if (validateCounter > 1) {
        submitLogin.disabled = false;
      }
    }
  });
}
submitLogin.addEventListener("click", login);
