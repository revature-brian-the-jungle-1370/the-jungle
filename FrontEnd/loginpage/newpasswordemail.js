const email = document.getElementById("emailInput")
const submitEmail = document.getElementById("submitEmail")
submitEmail.disabled = true
let loginStatus = false
const specialChar2 = /[ `^*()+=\[\]{};':"\\|,<>\/~]/
const invalidIcon = document.querySelectorAll("[id='invalid-icon']")
let invalidMessage = document.querySelectorAll("[id='email-invalid-message']")
let infoIcon = document.querySelectorAll(".info-icon")
const url = "http://ec2-52-200-53-62.compute-1.amazonaws.com:5000"
let validateCounter = 0

const div = document.getElementById("errorMessageGoesHere")
div.textContent = ""

async function checkEmailForResetPassword() {
    let response = fetch(url+"/user/reset-password", {
        method: "POST",
        mode: "cors",
        headers: { "Content-Type": "application/json"},
        body: JSON.stringify({
        email: email.value
        }),
    });
    console.log(response) //try grabbing response body with attribute for body
    if (response.status === 200) {
        console.log("200")
        let body = await response.json();
        console.log(body)
        //  Storing information for later
        localStorage.setItem("emailInput", JSON.stringify(body));
        window.location.href = "../loginpage/newpassword.html"; //  Redirect to Here????
    } else {
        div.textContent = "Invalid email";
    }
}

let jsonEmailObject = {
    email: "",
};

//  ----------------------------  Validation for inputs ----------------------------
email.addEventListener("focusin", resetPasswordEmail());
function resetPasswordEmail() {
    email.addEventListener("focusout", function () {
        if (email.value == "") {
            invalidIcon[0].style.display = "";
            infoIcon[0].style.display = "block";
            invalidMessage[0].textContent = "Email is incorrect or missing";
            } else if (specialChar2.test(email.value)) {
            invalidIcon[0].style.display = "";
            infoIcon[0].style.display = "block";
            invalidMessage[0].textContent =
            'Cannot contain spaces or special characters: `^*()+=[]{}"<>~|;:';
        } else {
            validateCounter += 1;
            invalidIcon[0].style.display = "none";
            invalidMessage[0].textContent = "";
            jsonEmailObject.email = email.value;
        }
        if (validateCounter > 0) {
            submitEmail.disabled = false;
        }
    });
}
submitEmail.addEventListener("click", checkEmailForResetPassword);
