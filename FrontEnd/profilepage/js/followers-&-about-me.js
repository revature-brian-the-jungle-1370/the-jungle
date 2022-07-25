const userBirthDate = document.getElementById("userBirthdateInput");
const userAboutMe = document.getElementById("userAboutMeInput");
const modalMessageDiv = document.getElementById("profileModalMsg");
const followerSectionDiv = document.getElementById("followers-div");
const groupSectionDiv = document.getElementById("groups-div");
const submitFollow = document.getElementById("follow-user-button");
const submitUnfollow = document.getElementById("unfollow-user-button");
const frontendUrl="http://127.0.0.1:5500/Frontend";
const pyUrl = "http://127.0.0.1:5000"

/*
    Grabs the user profile information from the update profile modal and sends it through the layers
*/
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

/*
    Reset the modal data when you close it
*/
function resetProfileModalData(){
    document.getElementById("updateUserProfileForm").reset()
    modalMessageDiv.innerHTML = '';
}

function setUpdatedProfileInfo(){
    let bDay=document.getElementById("birthday-display");
    let aboutMe=document.getElementById("about-me-display");
    aboutMe.innerText=userAboutMe.value;
    bDay.innerText=userBirthDate.value;
}

/*
    Function to print error message for update profile modal
*/
function errorMessageForProfileModal(){
    modalMessageDiv.innerHTML = '';
    let profileErrorMessage = document.createElement("p");
    profileErrorMessage.innerText = 'Birthdate may not be blank';
    profileErrorMessage.style.color = 'red';
    modalMessageDiv.append(profileErrorMessage);
}

/*
    Function to print success message for update profile modal
*/
function successMessageForProfileModal(){
    modalMessageDiv.innerHTML = '';
    let profileSuccessMessage = document.createElement("p");
    profileSuccessMessage.innerText = 'Saved';
    profileSuccessMessage.style.color = 'blue';
    modalMessageDiv.append(profileSuccessMessage);
}

async function getUserFollowers(){
    let url = "http://127.0.0.1:5000/user/followers/"+userId;

    let response = await fetch(url);
    console.log(response);
    if(response.status === 200){
        let body = await response.json();
        // console.log(body);
        populateUserFollowers(body);
        getFollowerImage(body);
    }
    else{
        //alert("Error with followers");
    }
}

function populateUserFollowers(followerBody){
    for(let follower in followerBody){
        // Created div to hold the image and username div and set class name
        let followerDiv = document.createElement("div");
        followerDiv.setAttribute("class", "follower-in-list");

        // Create the image tag and set class name
        let followerImage = document.createElement("img");
        followerImage.setAttribute("class", "friend");
        followerImage.setAttribute("id", `${follower}-image`);
        followerImage.setAttribute("alt", "No Image");
        followerImage.setAttribute("src", "img/default-profile-picture.jpg");

        // Created the username div and set the class name and username
        let followerUsernameDiv = document.createElement("div");
        followerUsernameDiv.setAttribute("class", "name valign-text-middle poppins-bold-astronaut-22px");
        followerUsernameDiv.innerHTML = `<a class="name valign-text-middle poppins-bold-astronaut-22px" href="profile-page.html?userId=${followerBody[follower]}">${follower}</a>`;

        // Append the created elements to the page
        followerSectionDiv.appendChild(followerDiv);
        followerDiv.appendChild(followerImage);
        followerDiv.appendChild(followerUsernameDiv);

    }
}

async function getFollowerImage(followerBody){
    for(follower in followerBody){
        let image_Element = document.getElementById(`${follower}-image`);
        let url = `http://localhost:5000/user/image/${followerBody[follower]}`;
        // console.log(url);
        let response = await fetch(url);
        if(response.status === 200){
            let image_text = await response.text();
            if(!image_text.includes("data:image")){
                image_text="data:image/PNG;base64,"+image_text;
            }
            image_Element.src = image_text;
        }

}
}

async function getGroupsForUser(){
    let url = "http://localhost:5000/group/user/"+userId

    let response = await fetch(url);

    if(response.status === 200){
        let body = await response.json();
        // console.log(body);
        populateGroupsForUsers(body);
    }
    else{
        alert("Error with groups");
    }
}

function populateGroupsForUsers(groupBody){
    for (let group in groupBody){
        let groupsDiv = document.createElement("div");
        groupsDiv.setAttribute("class", "group-in-list");

        let groupImage = document.createElement("img");
        groupImage.setAttribute("class", "friend");
        groupImage.setAttribute("alt", "No Image");
        groupImage.setAttribute("src", "img/default-profile-picture.jpg");

        let groupNameDiv = document.createElement("div");
        groupNameDiv.setAttribute("class", "name valign-text-middle poppins-bold-astronaut-22px");
        groupNameDiv.innerHTML = `<a id="groupLink-${groupBody[group].groupId}"`+
            ` class="name valign-text-middle poppins-bold-astronaut-22px"`+
            ` onclick=goToGroupPage(${groupBody[group].groupId})`+
            ` href="../grouppage/individualgrouppage/individual-group-page.html">${groupBody[group].groupName}</a>`;
        groupSectionDiv.appendChild(groupsDiv);
        groupsDiv.appendChild(groupImage);
        groupsDiv.appendChild(groupNameDiv);


    }
}

async function getGroupImage(groupBody){
    for(group in groupBody){
        let imageElement = document.getElementById(`${groupBody[group].groupName}-image`);
        let url = ""
    }
}

function goToGroupPage(groupId){
    let groupLink = getElementById("groupLink-" + groupId);
    // groupLink.setAttribute("href", "")
    localStorage.setItem("groupId", groupId);
}

async function follow_user(){
    let followJson = JSON.stringify({"user_follower_id": Number(loggedInUserId), "user_being_followed_id": Number(userId)});
    let followResponse = await fetch("http://localhost:5000/user/" + loggedInUserId + "/followed/" + userId, {
        method: "POST",
        mode: "cors",
        headers: {"Content-Type": "application/json"},
        body:followJson
    });
    console.log(followResponse);
    let followResponseBody = await followResponse.json();
    if(followResponse.status == 200){
        window.location.href = frontendUrl+"/profilepage/profile-page.html?userId="+loggedInUserId;
    } else {
        alert("Can only follow a user once.");
    }
    console.log(followResponseBody);
}
submitFollow.addEventListener("click", follow_user);

async function unfollow_user(){

    let unfollowJson = JSON.stringify({"user_follower_id": loggedInUserId, "user_being_followed_id": userId});
    let unfollowResponse = await fetch("http://127.0.0.1:5000/user/" + loggedInUserId + "/unfollowed/" + userId, {
        method: "POST",
        mode: "cors",
        headers: {"Content-Type": "application/json"},
        body:unfollowJson
    });
    let unfollowResponseBody = await unfollowResponse.json();
    if(unfollowResponse.status == 200){
        window.location.href = frontendUrl+"/profilepage/profile-page.html?userId="+loggedInUserId;
    } else {
        alert("You have already unfollowed that User");
    }
    console.log(unfollowResponseBody);
}
submitUnfollow.addEventListener("click", unfollow_user);

setProfileInfo();
getUserFollowers();
getGroupsForUser();