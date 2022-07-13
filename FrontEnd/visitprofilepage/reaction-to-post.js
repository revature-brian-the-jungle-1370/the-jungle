// let likeButton = document.getElementById("trigger");
// console.log("JS is connected to HTML");

// likeButton.onclick = 
async function likePost(e){

    let fetchJson = JSON.stringify({"postId": e})

    let url = "http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/postfeed"
    
    let theResponse = await fetch(url, {
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

 

    let response = await fetch(`http://ec2-52-200-53-62.compute-1.amazonaws.com:5000/postfeed/comment`, {
        method : "POST",
        body : JSON.stringify({
            commentId: 2
           
        })
        
    })

    let responseData = await response.json()
    console.log(responseData)


}

*/