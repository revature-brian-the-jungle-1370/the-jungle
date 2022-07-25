async function create_div_from_post_response(post){
  let postBox = document.createElement('div');
      
      //add the poster image
      let url = python_url + "user/image/" + post.user_id;
      let response = await fetch(url);
      let user_image_text;
      if(response.status === 200){
          user_image_text = await response.text();
          if(!user_image_text.includes("data:image")){
            user_image_text= "data:image/PNG;base64, "+user_image_text;
          }
      }

      //Get Username
      let post_username = await get_username(post.user_id)
  
      //Check if liked
      let post_response = await get_relevant_liketable_post(post.post_id)
      let heart_icon_path = post_response["message"] != "Like not found" ? "img/heart-icon@2x.svg" : "img/heart-icon-empty.svg"

      //Check if bookmarked
      bm_url = python_url + "bookmark/" + localStorage.getItem("userId") + "/" + post.post_id
      let bm_response = await fetch(bm_url);
      post_response = await bm_response.json()
      let bm_icon_path = post_response["message"] != "Bookmark not found" ? "img/bookmark_saved.svg" : "img/bookmark_unsaved.svg"
      //let bm_icon_path = "../img/bookmark_unsaved.svg"

      //get the post image
      url = python_url + "post/image/" + post.post_id;
      console.log(url);
      response = await fetch(url);
      console.log(response);
      let date_time = new Date(post.date_time_of_creation)
      let date = date_time.toDateString();
  
      if(response.status === 200){//if there is an image then this one, else the other one
        const image_text = await response.text();
        postBox.innerHTML =
        `<div class = "post" id = "post`+ post.post_id + `">
        <div class="flex-row">
          <div class="overlap-group2">
            <div class="new-york-ny valign-text-middle">`+ date +`</div>
            <div class="username-1 valign-text-middle poppins-bold-cape-cod-20px">`+ post_username +`</div>
            <img class="feed-avatar-1" src="`+ user_image_text + `" alt="img/ellipse-1@2x.png" />
          </div>
          <input type="image" class="three-dots-icon" src="img/trash-bin.svg"`+ 
             ` id="deletePost${post.post_id}" onclick="deleteGroupPost(${post.post_id})"/>
        </div>
        <img class="feed-picture" src="`+ image_text +`" />
        <div class="icon-container">
          <input type="image" class="heart-icon" src=`+ heart_icon_path +
          ` id="likePost_${post.post_id}" onclick="toggle_like_post(${post.post_id})" />
          <p id="post-like-count_${post.post_id}">` + post.likes + `</p>
          <input type="image" class="chat-bubble-icon" src="img/chat-bubble-icon@2x.svg"`+
            ` id="commentPost${post.post_id}" onclick="getComments(`+post.post_id+`)"/>
          <img class="share-icon" src="img/share-icon@2x.svg" />
          <input type="image" class="bookmark-icon" src=`+ bm_icon_path +
            ` id="bookmarkPost${post.post_id}" onclick="bookmark_post_as_user(${post.post_id})"/>
        </div>
        <div class="overlap-group-1">
        <div class="feed-text-2 valign-text-middle poppins-medium-black-18px">`+ post.post_text + `</div>
      </div>
      </div>`
      }else{
        postBox.innerHTML = 
      `<div class = "post" id = "post`+ post.post_id + `">
      <div class="flex-row">
        <div class="overlap-group2">
          <div class="new-york-ny valign-text-middle">`+ date +`</div>
          <div class="username-1 valign-text-middle poppins-bold-cape-cod-20px">`+ post_username +`</div>
          <img class="feed-avatar-1" src="`+ user_image_text + `" alt="img/ellipse-1@2x.png" />
        </div>
        <input type="image" class="three-dots-icon-1" src="img/trash-bin.svg" id="deletePost${post.post_id}" onclick="deletePost(${post.post_id})"/>
      </div>
      <div class="icon-container">
        <input type="image" class="heart-icon" src=`+ heart_icon_path +
        ` id="likePost_${post.post_id}" onclick="toggle_like_post(${post.post_id})" />
        <p id="post-like-count_${post.post_id}">` + post.likes + `</p>
        <input type="image" class="chat-bubble-icon" src="img/chat-bubble-icon@2x.svg"`+
            ` id="commentPost${post.post_id}" onclick="getComments(`+post.post_id+`)"/>
        <img class="share-icon" src="img/share-icon@2x.svg" />
        <input type="image" class="bookmark-icon" src=`+ bm_icon_path +
            ` id="bookmarkPost${post.post_id}" onclick="bookmark_post_as_user(${post.post_id})"/>
      </div>
      <div class="overlap-group-1">
      <div class="feed-text-2 valign-text-middle poppins-medium-black-18px">`+ post.post_text + `</div>
    </div>
    </div>`
    }
  
    return postBox
}

//----------------------------------------------- USER IMAGE ---------------------------------------------------------------
async function getPosterImage(user_id){
  let url = "http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/user/image/" + user_id;
  let response = await fetch(url);
  let user_image_text;
  if(response.status === 200){
      user_image_text = await response.text();
      if(!user_image_text.includes("data:image")){
        user_image_text= "data:image/PNG;base64,"+user_image_text;
      }
      return user_image_text;
    }
}
//----------------------------------------------- USERNAME ------------------------------------------------------------------
async function get_username(user_id){
  user_url = python_url + `user/${user_id}`
  let response = await fetch(user_url, {
    method: "GET"
  });
  if(response.status === 200){
    let body = await response.json()
    return body["username"]
  }
}

//----------------------------------------------- BOOKMARK POST FUNCTION-----------------------------------------------------

async function bookmark_post_as_user(post_id){
  bookmark_url = python_url + `bookmark/${localStorage.getItem("userId")}/${post_id}`
  let response = await fetch(bookmark_url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      'Accept': 'application/json'
    },
  });
  if(response.status === 200){
    let body = await response.json();
    console.log(body);
    toggle_bookmark_icon(post_id, body["message"])
  }
  else{
    let body = await response.json();
    console.log(body);
  }
}

function toggle_bookmark_icon(post_id, msg){
  let element = document.getElementById(`bookmarkPost${post_id}`)
  let cur_icon = element.getAttribute("src")
  cur_icon = msg === "Bookmark added" ? "img/bookmark_saved.svg" : "img/bookmark_unsaved.svg"
  element.setAttribute("src", cur_icon)
  //Remove post from bookmarks
  if( cur_icon === "img/bookmark_unsaved.svg" && sessionStorage.getItem("curr_feed") === "bkmk"){
    document.getElementById("post" + post_id).remove();
  }
}

function toggle_hide_div(div_element){
  if (div_element.style.display === "none"){
    div_element.style.display = "block";
  } else {
    div_element.style.display = "none";
  }
}

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
    cur_icon = msg === "Like added" ? "img/heart-icon@2x.svg" : "img/heart-icon-empty.svg"
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

async function getComments(post_id){
  let commentData= await fetch("http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/postfeed/"+post_id);
  if (commentData.status==200){
    let comments= await commentData.json()
    console.log(comments)
    let commentDiv=document.getElementById("comment-section-post"+post_id); 
    if(commentDiv!=null){
      commentDiv.innerHTML="";
    }
    for(let comment of comments){
      let image_text= await getPosterImage(comment.user_id);
      displayComment(comment,post_id,image_text);
    }
    addCommentBox(post_id);
  }
 }

function displayComment(commentJson,post_id,image_text){
  let post= document.getElementById("post"+post_id);
  let commentDiv=document.getElementById("comment-section-post"+post_id);
  let comment=document.createElement("div");
  if(commentDiv==null){
    commentDiv=document.createElement("div");
    commentDiv.setAttribute("id","comment-section-post"+post_id)
    commentDiv.setAttribute("class","d-flex flex-column");
  }
  commentDiv.appendChild(comment);
  comment.innerHTML=
  `<div class="d-flex comment-row" >
    <div class="overlap-group2 comment-avatar">
      <div class="username-1 valign-text-middle poppins-bold-cape-cod-20px">${commentJson.user_name}</div>
      <img class="feed-avatar-1" src="${image_text}" />
    </div>
    <div class="comments poppins-medium-black-18px" id=>
    `
    + commentJson.comment_text +
    `
    </div>
  </div>
  `;
  post.appendChild(commentDiv);
}

function addCommentBox(post_id){
  let commentDiv=document.getElementById("comment-section-post"+post_id); 
  let commentBox=document.createElement("div");
  let userImageFile=document.getElementById("userImageFile");
  commentBox.innerHTML=
  `<div class="d-flex comment-row" >
  <div class="overlap-group2 comment-avatar">
    <div class="username-1 valign-text-middle poppins-bold-cape-cod-20px">${JSON.parse(localStorage.getItem("userInfo")).username}</div>
    <img class="feed-avatar-1" src="${userImageFile.src}" />
  </div>
  <div class="comments poppins-medium-black-18px" >
    <input type="text" class="comment-box" id="comment-box-post`+post_id+`" placeholder="comment">
  </div>
  `;
  if(commentDiv==null){
    let post= document.getElementById("post"+post_id);
    commentDiv=document.createElement("div");
    commentDiv.setAttribute("id","comment-section-post"+post_id)
    commentDiv.setAttribute("class","d-flex flex-column");
    post.appendChild(commentDiv);
  }
  commentDiv.prepend(commentBox);
  commentBox.addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
      submitComments(post_id)
    }
});

}

async function submitComments(post_id){
  let commentRow=document.getElementById("comment-box-post"+post_id).parentElement.parentElement;
  let comment=document.getElementById("comment-box-post"+post_id);
  let commentText=comment.value;
  let url="http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/createComment";
  let response= await fetch (url,{
    method: 'POST',
    headers:{
      'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    "postId": post_id,
    "userId": loggedInUserId,
    "groupId": null,
    "replyUser": userId,
    "commentText": commentText
    })
  });

  if(response.status==200){
    let comment=document.createElement("div");
    let userImageFile=document.getElementById("userImageFile");
    comment.innerHTML=
    `<div class="d-flex comment-row" >
    <div class="overlap-group2 comment-avatar">
      <div class="username-1 valign-text-middle poppins-bold-cape-cod-20px">${JSON.parse(localStorage.getItem("userInfo")).username}</div>
      <img class="feed-avatar-1" src="${userImageFile.src}" />
    </div>
    <div class="comments poppins-medium-black-18px" >
      `+
      commentText
      +
      `
    </div>
    `;
    comment.value="";
    commentRow.parentNode.insertBefore(comment,commentRow.nextElementSibling);
  }  
}