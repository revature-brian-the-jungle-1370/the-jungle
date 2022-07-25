let tableBody = document.getElementById("postBody");

//--------------------------------------------------- CREATE GROUP POST FUNCTION-------------------------------------------------------

document.getElementById("sendGroupPostButton").addEventListener("click", createGroupPost);

async function createGroupPost() {

  let post_text = document.getElementById("postInput").value;
  let data = {
    "post_id": "0",
    "user_id": Number(localStorage.getItem("userId")),
    "group_id": Number(localStorage.getItem("groupId")),
    // "user_id": 10000,
    // "group_id": 9000,
    "post_text": post_text,
    "image_data": "",
    "likes": 0,
    "date_time_of_creation": ""
  }

  let response = await fetch(python_url + "group_post", {
    method: "POST",
    mode: "cors",
    headers: {
      "Content-Type": "application/json",
      'Accept': 'application/json'
    },
    redirect: "follow",
    body: JSON.stringify(data),
  });

  let responseBody = await response.json();
  if (response.status == 201) {
    //Code moved to util
    let thePost = await create_div_from_post_response(responseBody)
    document.getElementById("postInfo").append(thePost)
  } else {
    document.getElementById("postInfo").innerHTML = `Post could not be sent`
  }
}

//--------------------------------------------------- LOAD GROUP POST FUNCTION-------------------------------------------------------
async function getPost() {
  let group_id = Number(localStorage.getItem("groupId"));
  let response = await fetch(python_url + "group_post/group/" + group_id, { //replace with "/group_post/group/" + group_id
    method: "GET",
    mode: "cors",
  });
  if (response.status === 200) {
    let body = await response.json();
    console.log("getPost() igp: ")
    console.log(body);
    populateData(body);
  }
}

async function populateData(responseBody) {
  const allpost = document.getElementById("allpost");
  for (let post of responseBody) {
    //This code was moved to util_group_feed
    postBox = await create_div_from_post_response(post)
    allpost.appendChild(postBox)
  }
}

getPost()

//--------------------------------------------------- DELETE GROUP POST FUNCTION-------------------------------------------------------

async function deleteGroupPost(post_id) {
  let response = await fetch(python_url + "group_post/" + post_id, {
    method: "DELETE",
    mode: "cors"
  });
  // const body = await response.json();
  if (response.status === 200) {
    console.log("deleteGroupPost: success")
    document.getElementById("div_" + post_id).remove();
  }
}


//----------------------------------------------- LIKE AND UNLIKE GROUP POST FUNCTION-----------------------------------------------------

async function likePost(post_id) {
  let data = {
    "postId": post_id
  }
  let response = await fetch(python_url + "postfeed", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      'Accept': 'application/json'
    },
    body: JSON.stringify(data),
  });

  let result = await response.json()
  let count_element = document.getElementById(`post-like-count_${post_id}`)
  count_element.innerHTML = parseInt(count_element.innerHTML) + 1
}

async function unlikePost(post_id) {
  let data = {
    "postId": post_id
  }
  let response = await fetch(python_url + "postfeed/unlike", {
    method: "POST",
    mode: "cors",
    headers: {
      "Content-Type": "application/json",
      'Accept': 'application/json'
    },
    body: JSON.stringify(data),
  });

  let result = await response.json()
  let count_element = document.getElementById(`post-like-count_${post_id}`)
  count_element.innerHTML = parseInt(count_element.innerHTML) - 1
}

async function get_relevant_liketable_post(post_id){
  let liketable_url = python_url + "likes/" + localStorage.getItem("userId") + "/" + post_id
  let response = await fetch(liketable_url, {
    method: "GET",
    mode: "cors"
  });

  if(response.status === 200){
    console.log("get_relevant_liketable_post: Success")
    let result = await response.json()
    return result
  }else{
    console.log("get_relevant_liketable_post: Failed")
    return "Failed"
  }
}

async function update_relevant_liketable_post(post_id){
  let liketable_url = python_url + "likes/" + localStorage.getItem("userId") + "/" + post_id
  let response = await fetch(liketable_url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      'Accept': 'application/json'
    },
  });

  if(response.status === 200){
    let body = await response.json();
    console.log("update_relevant_liketable_post: Success")
    toggle_like_icon(post_id, body["message"])
  }
  else{
    console.log("update_relevant_liketable_post: Failed")
  }
}

async function toggle_like_post(post_id){
  // Get Post
  let liked_obj = await get_relevant_liketable_post(post_id)

  if (liked_obj["message"] === "Like not found"){
    await likePost(post_id)
  }else{
    await unlikePost(post_id)
  }
  await update_relevant_liketable_post(post_id)
}

function toggle_like_icon(post_id, msg){
  let element = document.getElementById(`likePost_${post_id}`)
  let cur_icon = element.getAttribute("src")
  cur_icon = msg === "Like added" ? "../img/heart-icon@2x.svg" : "../img/heart-icon-empty.svg"
  element.setAttribute("src", cur_icon)
}

//----------------------------------------------- COMMENTS ON GROUP POST FUNCTION-----------------------------------------------------
// TODO: Need Comments Functions
async function likeComment(comment_id) {

}

async function unlikeComment(comment_id) {

}

async function toggle_like_comment(post_id){
  let comment_url = python_url + "postfeed/" + post_id
  console.log("toggle_like_comment: " + post_url)
}

function toggle_comment_div(post_id){
  // let comment_info_element = document.getElementById(`comment_info_${post_id}`)
  // let comment_input_element = document.getElementById(`commentInput_${post_id}`)
  // let div_element = document.getElementById(`comment_div_${post_id}`)
  // //Clear out Error and Input area
  // comment_info_element.innerHTML = ""
  // comment_input_element.setAttribute("value", "")
  // //Toggle Div
  // if (div_element.style.display === "none") {
  //   div_element.style.display = "block";
  // } else {
  //   div_element.style.display = "none";
  // }
}

async function comment_post_as_user(post_id){
  // //Check input range
  // //Make fetch to database

  // //Toggle Div on success
  // toggle_comment_div(post_id)
}