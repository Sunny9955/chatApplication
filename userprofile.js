
async function userProfile()
{
    const userEmail=localStorage.getItem('useremail');
    const usertoken=localStorage.getItem('usertoken');
    const formData=new FormData();
    formData.append('userEmail',userEmail);
    const response=await fetch('http://localhost:8080/users/profile',{
        method:"POST",
        headers:{
            'Authorization': `Bearer ${usertoken}`
        },
        body:formData,
    });
    const userdetails = await response.json();
    console.log(userdetails);

    const profilebox = document.getElementById('profile-box');
   
    if (Array.isArray(userdetails)) {
        userdetails.forEach(user => {
            const profilediv = document.createElement('div');
            profilediv.textContent='Name: '+userdetails.username;
            profilediv.textContent = user.userEmail;
            profilebox.appendChild(profilediv);
        });
    } else {
        const profilediv = document.createElement('div');
        profilediv.textContent='Name: '+userdetails.username;
        profilediv.textContent = 'Email: '+userdetails.userEmail;
        profilebox.appendChild(profilediv);
    }

}
userProfile();