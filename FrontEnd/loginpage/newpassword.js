const passcode = document.getElementById("passcodeInput");
const submitPasscode = document.getElementById("submitPasscode");
submitPasscode.disabled = true;
let loginStatus = false;
const specialChar2 = /[ `^*()+=\[\]{};':"\\|,<>\/~]/;
const invalidIcon = document.querySelectorAll("[id='invalid-icon']");
let invalidMessage = document.querySelectorAll("[id='passcode-invalid-message']");
let infoIcon = document.querySelectorAll(".info-icon");
const url = "http://localhost:5000";
let validateCounter = 0;

const div = document.getElementById("errorMessageGoesHere");
div.textContent = "";

async function resetPassword() {
    let path = window.location.href;
    let userId = getUserId("http://localhost:5000/user/",path)
    console.log(userId)
    let response = await fetch(url+`/user/${userId}/reset-password`, {
        method: "POST",
        mode: "cors",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
        passcode: passcode.value
        }),
    });

    if (response.status === 200) {
        let body = await response.json();
        //  Storing information for later
        localStorage.setItem("passcodeInput", JSON.stringify(body));
        window.location.href = "../user/login.html"; //  Redirect to Here????
    } else {
        div.textContent = "Incorrect Username or Password";
    }
}

let jsonPasscodeObject = {
    passcode: "",
};

// ----------------------- Extract user id from the href --------------------
function getUserId(prefix,path){
    let userId = path.substring(prefix)
    userId = userId.toString().split("/")
    console.log(userId)
    return userId[0]
}
//  ----------------------------  Validation for inputs ----------------------------
passcode.addEventListener("focusin", resetPasswordCheck());
function resetPasswordCheck() {
    passcode.addEventListener("focusout", function () {
        if (email.value == "") {
            invalidIcon[0].style.display = "";
            infoIcon[0].style.display = "block";
            invalidMessage[0].textContent = "Email is incorrect or missing";
            } else if (specialChar2.test(passcode.value)) {
            invalidIcon[0].style.display = "";
            infoIcon[0].style.display = "block";
            invalidMessage[0].textContent =
            'Cannot contain spaces or special characters: `^*()+=[]{}"<>~|;:';
        } else {
            validateCounter += 1;
            invalidIcon[0].style.display = "none";
            invalidMessage[0].textContent = "";
            jsonPasscodeObject.passcode = passcode.value;
        }
        if (validateCounter > 0) {
            submitPasscode.disabled = false;
        }
    });
}
submitPasscode.addEventListener("click", resetPassword);