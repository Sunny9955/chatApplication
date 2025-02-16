async function startchatting(event)
{
    event.preventDefault();
    const username=document.querySelector('.recivername');
    console.log(username.value);
    sessionStorage.setItem('recivername', username.value);
    // console.log(localStorage.getItem('username'));

  window.location.href="chat.html";

}



async function signup(event)
{
  event.preventDefault();
  window.location.href="signup.html";
}

async function usersignup(event)
{
  const picture=document.querySelector('.image');
  const email=document.querySelector('.email');
  const name=document.querySelector('.name');
  const bio=document.querySelector('.bio');
  const password=document.querySelector('.password');
  console.log(name.value);
  console.log(password.value);
  const formData = new FormData();
        formData.append('email', email.value);
        formData.append('userName',name.value);
        formData.append('bio',bio.value);
        formData.append('picture',picture.files[0]);
        formData.append('password',password.value);
     
        const response =await fetch('http://localhost:8080/signup',{
          method:"POST",
          body:formData,
        });
        if(!response.ok){
          alert('Invalaid Username or Password');
        }
        else{
          alert('Successful');
          window.location.href="login.html";
        }
        
}


async function login(event)
{
 event.preventDefault();
 window.location.href="login.html";
}

async function userlogin(event)
{
  event.preventDefault();
  const name=document.querySelector('.name');
  const password=document.querySelector('.password');
  console.log(name.value);
  console.log(password.value);
  const response = await fetch('http://localhost:8080/login', {
    method: "POST",
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        email: name.value,
        password: password.value
    })
});

if (!response.ok) {
  if (response.status === 401) {
      alert('Invalid Username or Password');
  } else {
      throw new Error('Network response was not ok');
  }
  return;
}

const resource = await response.json();
console.log(resource);


if (resource.jwtToken) {
  localStorage.setItem('usertoken', resource.jwtToken); 
  localStorage.setItem('username',resource.userName);
  localStorage.setItem('useremail',resource.userEmail);
  console.log('userToken:', resource.jwtToken);
  console.log('UserName',localStorage.getItem('username'));
  window.location.href = "index.html"; 
} else {
  alert('You are Not Registered');
}

}
async function message(event)
{
  event.preventDefault();
  window.location.href="messenger.html";
  
}

async function invite(event) {
  event.preventDefault();
  window.location.href="invite.html";
 
}


function dropdown() {
  var dropdown = document.querySelector(".dropdown-profile");
  if (dropdown.style.display === "block") {
      dropdown.style.display = "none";
  } else {
      dropdown.style.display = "block";
  }
}
async function uploadvideo(event) {
  event.preventDefault();
  const vid = document.querySelector('#video'); 
  const bio = document.querySelector('.bio');
  console.log(bio.value);
  const usertoken=localStorage.getItem('usertoken');
    
  if (vid.files.length > 0) {
    const file = vid.files[0];
    console.log(file);

    const formData = new FormData();
    formData.append('video', file);
    formData.append('senderEmail', localStorage.getItem('useremail'));
    formData.append('senderUsername',localStorage.getItem('username'));
    formData.append('bio',bio.value);

    alert('It takes some time, please wait');
    try {
      const response = await fetch('http://localhost:8080/video/upload', {
        method: "POST",
        headers: {
          'Authorization': `Bearer ${usertoken}`
      },
        body: formData,
      });

      if (!response.ok) {
        throw new Error('Network response was not ok ' + response.statusText);
      }
      
      alert('Video uploaded successfully');
    } catch (error) {
      console.error('There has been a problem with your upload operation:', error);
    }

  } else {
    console.log('No video file selected');
  }
}


async function reels(event)
{
  event.preventDefault();
  window.location.href="reels.html";s
}

async function notification(event)
{
  event.preventDefault();
  window.location.href="notification.html";
}



