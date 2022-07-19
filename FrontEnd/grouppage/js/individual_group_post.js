let tableBody = document.getElementById("postBody");

//--------------------------------------------------- CREATE GROUP POST FUNCTION-------------------------------------------------------

document.getElementById("sendGroupPostButton").addEventListener("click", createGroupPost);

async function createGroupPost() {
  
  let post_text = document.getElementById("postInput").value;
  let data = {
    "post_id": "0",
    "user_id": Number(localStorage.getItem("user_id")),
    "group_id": Number(localStorage.getItem("group_id")),
    // "user_id": 10000,
    // "group_id": 9000,
    "post_text": post_text,
    "image_data": "",
    "likes": 0,
    "date_time_of_creation": ""
  }

  let response = await fetch(python_url + "/group_post", {
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
  let response = await fetch(python_url + "/group_post/group/10000", { //replace with "/group_post/group/" + group_id
    method: "GET",
    mode: "cors",
  });
  if (response.status === 200) {
    let body = await response.json();
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

// async function likePost() {}

// async function unlikePost() {}

//----------------------------------------------- COMMENTS ON GROUP POST FUNCTION-----------------------------------------------------
// TODO: Need Comments Functions