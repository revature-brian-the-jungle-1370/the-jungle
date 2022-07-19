// let likeButton = document.getElementById("trigger");
// console.log("JS is connected to HTML");

// likeButton.onclick = 
async function likePost(e){

    let fetchJson = JSON.stringify({"postId": e})
    let final_url = java_url + "/postfeed"
    
    let theResponse = await fetch(final_url, {
        method:"POST",
        headers:{'Content-Type': 'application/json'}, 
        body:fetchJson}).then(response => {return response.json()});

    console.log(theResponse)
    let heart = document.getElementById("likedComment" + e);
    heart.innerHTML = theResponse;
  
}

/*
commentButton.onclick = async function(e){
    e.preventDefault();

    let final_url = java_url + "/postfeed/comment"
    let response = await fetch(final_url, {

        method : "POST",
        body : JSON.stringify({
            commentId: 2
           
        })
        
    })

    let responseData = await response.json()
    console.log(responseData)


}

*/