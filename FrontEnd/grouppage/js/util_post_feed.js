//let python_url =  "http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/";
//let java_url =    "http://ec2-52-200-53-62.compute-1.amazonaws.com:8080/";
// let python_url = "http://127.0.0.1:5000/"
// let java_url =    "http://127.0.0.1:8080/";

async function create_div_from_post_response(post){
    //Create Div
    let postBox = document.createElement('div');
    postBox.setAttribute("id", `div_${post.post_id}`);

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

    //get Username
    let post_username = await get_username(post.user_id)

    //get the post image
    url = python_url + "post/image/" + post.post_id;
    response = await fetch(url);
    let date_time = new Date(post.date_time_of_creation)
    let date_2 = date_time.toDateString();

    //Check if bookmarked
    bm_url = python_url + "bookmark/" + localStorage.getItem("userId") + "/" + post.post_id
    let bm_response = await fetch(bm_url);
    let post_response = await bm_response.json()
    let bm_icon_path = post_response["message"] != "Bookmark not found" ? "../img/bookmark_saved.svg" : "../img/bookmark_unsaved.svg"
    //let bm_icon_path = "../img/bookmark_unsaved.svg"

    //Check if liked
    post_response = await get_relevant_liketable_post(post.post_id)
    let heart_icon_path = post_response["message"] != "Like not found" ? "../img/heart-icon@2x.svg" : "../img/heart-icon-empty.svg"

    if(response.status === 200){
        const image_text = await response.text();
        postBox.innerHTML =
      `<div class="flex-row">
        <div class="overlap-group">
          <div class="new-york-ny valign-text-middle poppins-bold-pink-swan-14px"> `+ date_2 +` </div>
          <div class="username-1 valign-text-middle poppins-bold-cape-cod-20px">`+ post_username +`</div>
          <img class="feed-avatar" src="`+ user_image_text  + `" alt="user_image_text" />
        </div>
        <input type="image" class="three-dots-icon" src="img/trash-bin.svg"`+ 
            ` id="deletePost${post.post_id}" onclick="deleteGroupPost(${post.post_id})"/>
      </div>
      <img class="feed-picture" src="`+ image_text +`"/>
      <div class="icon-container">
        <input type="image" class="heart-icon" src=`+ heart_icon_path +
            ` id="likePost_${post.post_id}" onclick="toggle_like_post(${post.post_id})" />
        <p id="post-like-count_${post.post_id}>` + post.likes + `</p>
        <input type="image" class="chat-bubble-icon" src="img/chat-bubble-icon@2x.svg"`+
            ` id="commentPost${post.post_id}" onclick="toggle_comment_div(${post.post_id})"/>
        <img class="share-icon" src="img/share-icon@2x.svg" />
        <input type="image" class="bookmark-icon" src=`+ bm_icon_path +
            ` id="bookmarkPost${post.post_id}" onclick="bookmark_post_as_user(${post.post_id})"/>
      </div>
      <div class="overlap-group2">
        <div class="feed-text-2 valign-text-middle poppins-medium-black-18px"><p>`+ post.post_text +`</p></div>
      </div>`
      }else{
        postBox.innerHTML =
      `<div class="flex-row">
        <div class="overlap-group">
          <div class="new-york-ny valign-text-middle poppins-bold-pink-swan-14px"> `+ date_2 +` </div>
          <div class="username-1 valign-text-middle poppins-bold-cape-cod-20px">`+ post_username + `</div>
          <img class="feed-avatar" id="UserImage`+ post.post_id +`" src="`+ user_image_text  + `" />
        </div>
        <input type="image" class="three-dots-icon" src="img/trash-bin.svg"`+
            ` id="deletePost${post.post_id}" onclick="deleteGroupPost(${post.post_id})"/>
      </div>
      <div class="icon-container">
        <input type="image" class="heart-icon" src=`+ heart_icon_path +
        ` id="likePost_${post.post_id}" onclick="toggle_like_post(${post.post_id})" />
        <p id="post-like-count_${post.post_id}">` + post.likes + `</p>
        <input type="image" class="chat-bubble-icon" src="img/chat-bubble-icon@2x.svg"`+
            ` id="commentPost${post.post_id}" onclick="toggle_comment_div(${post.post_id})"/>
        <img class="share-icon" src="img/share-icon@2x.svg" />
        <input type="image" class="chat-bubble-icon" src=`+ bm_icon_path +
            ` id="bookmarkPost${post.post_id}" onclick="bookmark_post_as_user(${post.post_id})"/>
      </div>
      <div class="overlap-group2">
        <div class="feed-text-2 valign-text-middle poppins-medium-black-18px"><p>`+ post.post_text +`</p></div>
      </div>`
      }

    return postBox
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
  url = python_url + `bookmark/${localStorage.getItem("userId")}/${post_id}`
  let response = await fetch(url, {
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
  cur_icon = msg === "Bookmark added" ? "../img/bookmark_saved.svg" : "../img/bookmark_unsaved.svg"
  element.setAttribute("src", cur_icon)
}

function toggle_hide_div(div_element){
  if (div_element.style.display === "none"){
    div_element.style.display = "block";
  } else {
    div_element.style.display = "none";
  }
}