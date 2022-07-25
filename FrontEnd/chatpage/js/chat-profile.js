let loggedInUserId = JSON.parse(localStorage.getItem("userInfo")).userId; 

const userBirthDate = document.getElementById("userBirthdateInput");
const userAboutMe = document.getElementById("userAboutMeInput");
const modalMessageDiv = document.getElementById("profileModalMsg");

function setProfileInfo(){
    let name=document.getElementById("name-display");
    let username=document.getElementById("username-display");
    let bDay=document.getElementById("birthday-display");
    let aboutMe=document.getElementById("about-me-display");
  
    let visitedUser;
    getProfileUser(loggedInUserId,"userInfo");

    visitedUser=JSON.parse(localStorage.getItem("userInfo"));
    if(name && username && bDay && aboutMe){
        name.innerText=visitedUser.firstName+" "+visitedUser.lastName
        username.innerText="@"+visitedUser.username

        let bd=new Date(visitedUser.birthday);
        bDay.innerText=bd.getMonth()+"/"+bd.getDate()+"/"+bd.getFullYear();
        aboutMe.innerText="about me:\n"+visitedUser.aboutMe 
    }
}

function setUpdatedProfileInfo(){
  let bDay=document.getElementById("birthday-display");
  let aboutMe=document.getElementById("about-me-display");
  aboutMe.innerText=userAboutMe.value;
  bDay.innerText=userBirthDate.value;
}

function successMessageForProfileModal(){
  modalMessageDiv.innerHTML = '';
  let profileSuccessMessage = document.createElement("p");
  profileSuccessMessage.innerText = 'Saved';
  profileSuccessMessage.style.color = 'blue';
  modalMessageDiv.append(profileSuccessMessage);
}

function errorMessageForProfileModal(){
  modalMessageDiv.innerHTML = '';
  let profileErrorMessage = document.createElement("p");
  profileErrorMessage.innerText = 'Birthdate may not be blank';
  profileErrorMessage.style.color = 'red';
  modalMessageDiv.append(profileErrorMessage);
}

/**
 * 
 * visitedUser	{"email":"email",
 * "first_name":"first name",
 * "last_name":"last name",
 * "passcode":"newpasscode",
 * "user_about":"smiley",
 * "user_birth_date":"Mon, 18 Jul 2022 00:00:00 GMT","
 * user_id":10000,
 * "user_image_format":"png",
 * "username":"username"}
 */

async function getProfileUser(userId,key){
    let response = await fetch("http://localhost:5000/user/"+userId);
    if (response.status === 200) {
      let body = await response.json();
      //  Storing information for later
      let convertedUser=JSON.stringify({//set to keys thats used by previous code or else exception
        "userId":body.user_id,
        "firstName":body.first_name,
        "lastName":body.last_name,
        "aboutMe":body.user_about,
        "birthday":body.user_birth_date,
        "username":body.username
    })
      localStorage.setItem(key, convertedUser);
  }
}

async function updateUserProfileData(){
  // Will need to update this to use the current user's ID
  let url = "http://localhost:5000/user/profile/update/"+loggedInUserId;
  let updateUserProfileJSON = JSON.stringify({
  "firstName": "Shouldn't change",
  "lastName": "Shouldn't change",
  "email": "Shouldn't change",
  "username": "Shouldn't change",
  "passcode": "Shouldn't change",
  "userAbout": userAboutMe.value,
  "userBirthDate": userBirthDate.value,
  "userImageFormat": "Shouldn't change"
});

  let response = await fetch(url, {
      method: "PATCH",
      headers:{"Content-Type": 'application/json'},
      body:updateUserProfileJSON})

      if(response.status === 200){
          let body = await response.json();
          successMessageForProfileModal();
          setUpdatedProfileInfo()
          // console.log(body);
      }
      else{
          errorMessageForProfileModal();
      }
}



setProfileInfo();