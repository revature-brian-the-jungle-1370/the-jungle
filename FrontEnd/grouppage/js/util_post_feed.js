//let python_url =  "http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/";
//let java_url =    "http://ec2-52-200-53-62.compute-1.amazonaws.com:8080/";
// let python_url = "http://127.0.0.1:5000/"
// let java_url =    "http://127.0.0.1:8080/";
localStorage.setItem("user_id", 13)
localStorage.setItem("group_id", 10000) //Comment this out later. This is to test functionality

async function create_div_from_post_response(post){
    //Create Div
    let postBox = document.createElement('div');
    postBox.setAttribute("id", `div_${post.post_id}`);

    //add the poster image
    let url = python_url + "user/image/" + post.user_id;
    let response = await fetch(url);
    let user_image_text;
    if(response.status === 200){
        user_image_text = await response.text();}

    //get the post image
    url = python_url + "post/image/" + post.post_id;
    console.log(url);
    response = await fetch(url);
    console.log(response);
    let date_time = new Date(post.date_time_of_creation)
    let date_2 = date_time.toDateString();

    if(response.status === 200){
        const image_text = await response.text();
        postBox.innerHTML =
      `<div class="flex-row">
        <div class="overlap-group">
          <div class="new-york-ny valign-text-middle poppins-bold-pink-swan-14px"> `+ date_2 +` </div>
          <div class="username-1 valign-text-middle poppins-bold-cape-cod-20px">JostSNL21</div>
          <img class="feed-avatar" src="data:image/PNG;base64,`+ user_image_text  + `" alt="user_image_text" />
        </div>
        <input type="image" class="three-dots-icon" src="img/three-dots-icon-1@2x.svg" id="deletePost${post.post_id}" onclick="deleteGroupPost(${post.post_id})"/>
      </div>
      <img class="feed-picture" src="`+ image_text +`"/>
      <div class="icon-container">
        <input type="image" class="heart-icon" src="img/heart-icon@2x.svg" />
        <p>` + post.likes + `</p>
        <input type="image" class="chat-bubble-icon" src="img/chat-bubble-icon@2x.svg"/>
        <img class="share-icon" src="img/share-icon@2x.svg" />
      </div>
      <div class="overlap-group2">
        <div class="feed-text-2 valign-text-middle poppins-medium-black-18px"><p>`+ post.post_text +`</p></div>
      </div>`
      }else{
        postBox.innerHTML =
      `<div class="flex-row">
        <div class="overlap-group">
          <div class="new-york-ny valign-text-middle poppins-bold-pink-swan-14px"> `+ date_2 +` </div>
          <div class="username-1 valign-text-middle poppins-bold-cape-cod-20px">JostSNL21</div>
          <img class="feed-avatar" id="UserImage`+ post.post_id +`" src="data:image/PNG;base64,`+ user_image_text  + `" />
        </div>
        <input type="image" class="three-dots-icon" src="img/three-dots-icon-1@2x.svg" id="deletePost${post.post_id}" onclick="deleteGroupPost(${post.post_id})"/>
      </div>
      <div class="icon-container">
        <input type="image" class="heart-icon" src="img/heart-icon@2x.svg" />
        <p>` + post.likes + `</p>
        <input type="image" class="chat-bubble-icon" src="img/chat-bubble-icon@2x.svg"/>
        <img class="share-icon" src="img/share-icon@2x.svg" />
      </div>
      <div class="overlap-group2">
        <div class="feed-text-2 valign-text-middle poppins-medium-black-18px"><p>`+ post.post_text +`</p></div>
      </div>`
      }
    
    return postBox
}