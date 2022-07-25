let params = new URLSearchParams(location.search);
let userId=params.get('userId');
let loggedInUserId = JSON.parse(localStorage.getItem("userInfo")).userId; 
let python_url =  "http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/";
let java_url =    "http://ec2-52-200-53-62.compute-1.amazonaws.com:8080/";

//et currentUsername=JSON.parse(localStorage.getItem("userInfo")).username; 
(function () {
  console.log(document.getElementById("label-for-profil-img"));
  let createPostModal=document.getElementById("createPostModal");
  if(userId==null){
    userId=loggedInUserId;
  }
  if(createPostModal!=null && userId!=loggedInUserId){
    // console.log("hjhfdbsjdbjsbdhb")
    createPostModal.remove();
    document.getElementById("createPostBtn").remove();
    document.getElementById("userImageFileInput").remove();
    document.getElementById("updateProfileEditProfileBtn").remove();
    //currentUsername=JSON.parse(localStorage.getItem("visitedUser")).username; 
  }
})();


function setProfileInfo(){
  let name=document.getElementById("name-display");
  let username=document.getElementById("username-display");
  let bDay=document.getElementById("birthday-display");
  let aboutMe=document.getElementById("about-me-display");

  let visitedUser;
  if(userId!=loggedInUserId){
      getProfileUser(userId,"visitedUser");
     visitedUser=JSON.parse(localStorage.getItem("visitedUser"));
  }
  else{
      getProfileUser(loggedInUserId,"userInfo");

      visitedUser=JSON.parse(localStorage.getItem("userInfo"));
  }
  if(name && username && bDay && aboutMe){
      name.innerText=visitedUser.firstName+" "+visitedUser.lastName
      username.innerText="@"+visitedUser.username

      let bd=new Date(visitedUser.birthday);
      bDay.innerText=bd.getMonth()+"/"+bd.getDate()+"/"+bd.getFullYear();
      aboutMe.innerText="about me:\n"+visitedUser.aboutMe
  }
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
  let response = await fetch("http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/user/"+userId);
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


// this is just a proof of concept and does not contain styling elements of the finished code
//assuming you are getting all the posts at once, this method will have to be called individually in a for loop for each post
//rough method to get the post image from database, needs to be updated to get the image format
//please refactor and modify as needed
async function getPostImage(){// the postId and imageFormat will probably have to be passed as parameters
  let url = "http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/post/image/" + postId;//post_id parameter
  console.log(url);
  let response = await fetch(url);
  console.log(response);

  if(response.status === 200){

      const image_text = await response.text();
      const image_Element = document.createElement('img');
      image_Element.src = image_text;//image_parameter
      document.getElementById("postImage").appendChild(image_Element);// also the element id will have to be dynamically created for each post so that the image is placed on the correct post
  }
}




// A basic create post without images for users.
async function createPost(){
  try{
  let postText = document.getElementById("postText");
    console.log(postText.value)
    if(postText.value.length > 500 || postText.value.length==0){
      alert('Post can only contain 1-500 characters')
    }
    let postJson = JSON.stringify({"user_id":userId, "post_text": postText.value, "image_format": "false"});
    let url = "http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/post"
    let thePost = await fetch(url, {
        method:"POST",
        headers:{'Content-Type': 'application/json'}, 
        body:postJson}).then(response => {return response.json()});
    console.log(thePost);
    
  }catch(error){
    error.status = 400;
  }
}



// A more complicated create post with images for users.
async function createPostWithImage() {
    let file    = document.getElementById('imageFile').files[0];
    let reader  = new FileReader();
    let base64gif;
  
    reader.addEventListener("load", async function () {
      base64gif = reader.result;
      console.log(base64gif.slice(11, 14));


      if (base64gif.length < 1_000_000 && base64gif.startsWith("data:image/")){
        let postText = document.getElementById("postText");
        let postJson = JSON.stringify({"user_id":userId, "post_text": postText.value, "image_format": "true"});
        let url = "http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/post"
        
        //Inserts the post into the post table
        let thePost = await fetch(url, {
            method:"POST",
            headers:{'Content-Type': 'application/json'}, 
            body:postJson}).then(response => {return response.json()});

        //Inserts the image into the post_image_table
        console.log(thePost["post_id"]);
        let response = await fetch(
            "http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/post/image/" + thePost["post_id"], {
              method: "POST",
              headers: {"Content-Type": "application/json"},
              body: String(base64gif)
          });
          const imageText = await response.text();
          console.log(imageText)
      
      }
      else{
        alert("File Error")//need to replace this alert method later
      }
    }, false);
    
    if (file) {
      reader.readAsDataURL(file);
    }else if (document.getElementById("postText")){ // if there is no file put in, then the post is sent with the simpler method as long as there is text
      createPost()
    }else{
      alert("No Post entered.")//need to replace this alert method later
    }
    
    document.getElementById("createPostForm").reset();//because I don't know how to use PHP
  }


  async function getBookMarkedPost() {
    sessionStorage.setItem("curr_feed", "bkmk")
    let response = await fetch(python_url + "bookmark/" + userId, {     //All bookmarked posts
      method: "GET",
      mode: "cors"
    });
    if (response.status === 200) {
      let body = await response.json();
      populateData(body);
    }
  }
  

  async function getPost() {
    sessionStorage.setItem("curr_feed", "all")
    let response = await fetch(python_url + "user/post/" + userId, {    //All user Posts
      method: "GET",
      mode: "cors"
    });
    if (response.status === 200) {
      let body = await response.json();
      populateData(body);
    }
  }
  
  async function populateData(responseBody) {
    const allpost = document.getElementById("post column");
    allpost.innerHTML = '';
    for (let post of responseBody){
      let postBox = await create_div_from_post_response(post)
      allpost.appendChild(postBox)
    }
  }
  
  function setFeed(curr_feed){
    sessionStorage.setItem("curr_feed", curr_feed);
    window.location.href = "./profile-page.html?userId="+userId;
  }

  function setAllFeed(){
    setFeed("all")
  } 
  
  function setBookmarkFeed(){
    setFeed("bkmk")
  }

  async function deletePost(post_id) {
    let deleteResponse = await fetch("http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/group_post/" + post_id, {
      method: "DELETE"
    })
    console.log(deleteResponse)
    if (deleteResponse.status === 200) {
      document.getElementById("post" + post_id).remove();
    }
  }

  async function setup_profile_page_first_time(){
    document.getElementById("all_post_img_feed").addEventListener("click", setAllFeed)
    document.getElementById("bkmk_post_img_feed").addEventListener("click", setBookmarkFeed)
    // Setup Feed on first loading
    let curr_feed = sessionStorage.getItem("curr_feed")
    if(curr_feed !== "all" && curr_feed !== "bkmk"){
      sessionStorage.setItem("curr_feed", "all")
    }
    curr_feed = sessionStorage.getItem("curr_feed")
    sessionStorage.getItem("curr_feed") === "all" ? await getPost() : await getBookMarkedPost()
  }

  setup_profile_page_first_time()
