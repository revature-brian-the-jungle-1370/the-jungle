
async function updateProfile(){
    let url="http://127.0.0.1:8080/user";
    let updatedUser= await fetch(url,{
        headers:{
            'Content-Type': 'application/json'
        },
        method: 'PATCH',
        body: JSON.stringify({
            "title": title.value,
            "severity":severity.value,
            "urgency":urgency.value,
            "description":description.value,
            "creator_id":creatorId
        })
    });
}