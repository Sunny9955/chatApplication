notificationCheck();
async function notificationCheck()
{
    const receiverEmail=localStorage.getItem('useremail');
    const usertoken=localStorage.getItem('usertoken');
    console.log(usertoken);
    console.log(receiverEmail);
    const formData=new FormData();
    formData.append('receiverEmail',receiverEmail);
    const response=await fetch('http://localhost:8080/pending/message/notification',
        {
            method:"POST",
            headers:{
                'Authorization': `Bearer ${usertoken}`
            },
            body:formData,

        }
    );
    const pendingmessage=await response.json();
    if (pendingmessage && pendingmessage.length > 0) {
        const notificationBox = document.getElementById('notification-box');

       
        notificationBox.innerHTML = '';

       
        pendingmessage.forEach(message => {
            const messageElement = document.createElement('div');
            messageElement.textContent = message.senderUsername+ ": " + message.content;
            messageElement.style.color = 'green'; 
            notificationBox.appendChild(messageElement);
        });

    
       
    } 
    else{
        const notificationBox = document.getElementById('notification-box');
        notificationBox.textContent='Empty';
    }
}