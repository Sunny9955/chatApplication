async function invite() {
    const usertoken=localStorage.getItem('usertoken');
    console.log(usertoken);

    const curruser=localStorage.getItem('useremail');
     console.log(curruser);
    const formData=new FormData();
    formData.append('userEmail',curruser);
    const response = await fetch('http://localhost:8080/getfriend',{
        method:"POST",
        headers: {
            'Authorization': `Bearer ${usertoken}`
        },
        body:formData,
});
    const userFriendData = await response.json();
    console.log(userFriendData );
    localStorage.setItem('userFriendData', JSON.stringify(userFriendData));
    // Set the username in the sessionStorage
    //sessionStorage.setItem('username', userData.username); 
    printUsers(); 
}
function showMessage(user) {
    var userbox = document.getElementById("friend-box");

    var container = document.createElement("div");
    container.style.display = "flex";
    container.style.alignItems = "center";

    var image = document.createElement("img"); 
    image.src = user.profilePicture; 
    image.alt = "Profile Picture"; 
    image.style.width = "50px";
    image.style.height = "50px"; 
    image.style.borderRadius = "50%"; 
    image.style.transition = "transform 0.2s ease"; 

    image.addEventListener("click", function(event) {
        event.stopPropagation(); 
        if (image.style.transform === "scale(5)") {
            image.style.transform = "scale(1)"; 
        } else {
            image.style.transform = "scale(5)"; 
        }
    });

    var element = document.createElement("p");
    element.textContent = user.username;

    container.appendChild(image); // Append the image to the container
    container.appendChild(element);
    userbox.appendChild(container);
    container.addEventListener("click", function() {
        localStorage.setItem('receiver', user.userEmail);
        localStorage.setItem('userprofilepicture',user.profilePicture);
        localStorage.setItem('receiverUserName',user.username);
        //  console.log('reciver',localStorage.getItem('receiver'));
        window.location.href = "chat.html";
    });
}



async function printUsers() {
    const data = localStorage.getItem('userFriendData');
    
    if (data) {
        const userData = JSON.parse(data);
        const userContainer = document.querySelector('#friend-box');
        userContainer.innerHTML = '';
        userData.forEach(user => {
            showMessage(user);
        });
    }
}

invite();