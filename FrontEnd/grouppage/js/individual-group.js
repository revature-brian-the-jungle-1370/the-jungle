/** -----------------------------------------------------Join Group------------------------------------------------------------ */

async function joinGroup() {
    // const groupId = localStorage.getItem("groupId").value;
    const userId = localStorage.getItem("userId");
    const groupId = localStorage.getItem("groupId");
    //const userId = 9000;  //dont use it is hard coded

    let response = await fetch(url + `/group/join/${groupId}/${userId}`, {method: "POST", mode: "cors",
        headers: {"Content-Type": "application/json"}});

    await response.json();

    if (response.status === 200) {
        const groupJoined = document.getElementById("groupJoined");
        groupJoined.style.display = "block";
        setTimeout(fade_out, 5000);
        const hideJoinButton = document.getElementById("submitJoinGroup");
        hideJoinButton.style.display = "none";
    }
    else {
        const groupNotJoined = document.getElementById("groupNotJoined");
        groupNotJoined.style.display = "block";
    }
}

function fade_out() {
    document.getElementById("groupJoined").style.display = "none";
}

const submitJoinGroup = document.getElementById("submitJoinGroup");
submitJoinGroup.addEventListener("click", joinGroup);