const passcode = document.getElementById("passcodeInput");
const submitPasscode = document.getElementById("submitPasscode");
submitPasscode.disabled = true;
let loginStatus = false;
const specialChar2 = /[ `^*()+=\[\]{};':"\\|,<>\/~]/;
const invalidIcon = document.querySelectorAll("[id='invalid-icon']");
let invalidMessage = document.querySelectorAll("[id='passcode-invalid-message']");
let infoIcon = document.querySelectorAll(".info-icon");
const frontendUrl = "http://s3.amazonaws.com/dans-code.net"
const url = "http://ec2-52-200-53-62.compute-1.amazonaws.com:5000";
// const frontendUrl = "http://127.0.0.1:5500"
// const url = "http://127.0.0.1:5000";
let validateCounter = 0;

const div = document.getElementById("errorMessageGoesHere");
div.textContent = "";

async function resetPassword() {
    let retrievedUserId = window.localStorage.getItem("user_id")
    console.log(retrievedUserId)
    let user_id = JSON.parse(retrievedUserId)
    let response = await fetch(url+`/user/${user_id}/new-password`, {
        method: "POST",
        mode: "cors",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
        passcode: passcode.value
        }),
    });
    console.log(response)
    let validatePassword = specialChar2.test(response.passcode)
    console.log(validatePassword)
    if (response.status == 200) {
        let body = await response.json();
        console.log(body)
        window.localStorage.clear()
        window.location.href = frontendUrl+"/FrontEnd/loginpage/login.html"; //  Redirect to Here????
    } else if(validatePassword == false){
        div.textContent = "Invalid Password";
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
        if (passcode.value == "") {
            invalidIcon[0].style.display = "";
            infoIcon[0].style.display = "block";
            invalidMessage[0].textContent = "Password is incorrect or missing";
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
