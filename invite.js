async function invite() {
    const usertoken=localStorage.getItem('usertoken');

    const curruser=localStorage.getItem('useremail');
    console.log(curruser);
    const formData=new FormData();
    formData.append('userEmail',curruser);
    const response = await fetch('http://localhost:8080/getUser',{
        method:"POST",
        headers: {
            'Authorization': `Bearer ${usertoken}`
        },
        body:formData,
});
    const userData = await response.json();
    console.log(userData);
    localStorage.setItem('userData', JSON.stringify(userData));
    // Set the username in the sessionStorage
    //sessionStorage.setItem('username', userData.username); 
    printUsers(); 
}

function showMessage(user) {
    var userbox = document.getElementById("userContainer");

    var container = document.createElement("div");
    container.style.display = "flex";
    container.style.alignItems = "center";

    var image = document.createElement("img"); // Create an img element
    image.src = user.profilePicture; // Set the src attribute to the user's profile picture URL
    image.alt = "Profile Picture"; // Set the alt attribute
    image.style.width = "50px"; // Set the width of the image
    image.style.height = "50px"; // Set the height of the image
    image.style.borderRadius = "50%"; // Make the image circular

    var element = document.createElement("p");
    element.textContent = user.username;
    element.style.margin = "0 10px"; // Add some margin between the image and the username

    var button = document.createElement("button");
    button.className = "btn btn-primary";
    button.textContent = "Invite";

    button.addEventListener("click", function() {
        localStorage.setItem('name', user.userEmail);
        inviteuser(button); 
    });

    container.appendChild(image); // Append the image to the container
    container.appendChild(element);
    container.appendChild(button);

    userbox.appendChild(container);
}


async function printUsers() {
    const data =localStorage.getItem('userData');
    
    if (data) {
        const userData = JSON.parse(data);
        const userContainer = document.querySelector('#userContainer');
        userContainer.innerHTML = '';
        userData.forEach(user => {
            showMessage(user);
        });
    }
}

invite();
async function inviteuser(button) {
    if (button.textContent === "Invited") {
        alert('You are already invited');
    } else {
        const userfromfollow = localStorage.getItem('useremail'); // Correct key is 'username'
        const usertofollow = localStorage.getItem('name'); // Correct key if 'name' is set elsewhere in the code
        console.log(userfromfollow);
        console.log(usertofollow);

        if (!userfromfollow) {
            alert('Username not found in session storage.');
            return;
        }
        const usertoken=localStorage.getItem('usertoken');

        const formData = new FormData();
        formData.append('userFromFollow', userfromfollow);
        formData.append('userToFollow', usertofollow);

        const response = await fetch('http://localhost:8080/invite', {
            method: "POST",
            headers: {
                'Authorization': `Bearer ${usertoken}`
            },
            body: formData,
        });

        if (!response.ok) {
            alert('Invalid Username or Password');
        } else {
            alert('Successful');
            button.textContent = "Invited";
        }

        sessionStorage.removeItem('name');
    }
}

async function search(event) {
    const searchinput = document.querySelector('.searchinput');
    const usertoken=localStorage.getItem('usertoken');
   
    const formData = new FormData();
    formData.append('userEmail', searchinput.value); 
    searchinput.value = ''; 

    const response = await fetch('http://localhost:8080/search/user', {
        method: 'POST', 
        headers: {
            'Authorization': `Bearer ${usertoken}`
        },
        body: formData,
    });

    const searchdata = await response.json();
    console.log(searchdata);
    sessionStorage.setItem('searchdata',searchdata);
    printUsers();
}

