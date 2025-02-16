// var stompClient = null;
// var username = sessionStorage.getItem('username') || null;
// console.log(username);

// function setUsername() {
//     username = document.getElementById('username').value.trim();
//     if (username) {
//         sessionStorage.setItem('username', username);
//         connect();
//     }
// }
// function connect() {
//     if (username) {
//         var socket = new SockJS('http://127.0.0.1:8080/chat');
//         stompClient = Stomp.over(socket);
//         stompClient.connect({}, function(frame) {
//             console.log('Connected: ' + frame);
//             document.getElementById('login').style.display = 'none';
//             document.getElementById('chat-room').style.display = 'block';
//             document.getElementById('private-chat-room').style.display = 'block';
//             stompClient.subscribe('/chatroom/public', function(messageOutput) {
//                 showMessage(JSON.parse(messageOutput.body), 'messages');
//             });
//             stompClient.subscribe('/user/' + username + '/private', function(messageOutput) {
//                 showMessage(JSON.parse(messageOutput.body), 'private-messages');
//             });
//         });
//     }
// }

// function sendMessage() {
//     var messageContent = document.getElementById('message-input').value.trim();
//     if (messageContent && stompClient) {
//         var chatMessage = {
//             message: messageContent,
//             senderName: username
//         };
//         stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
//         document.getElementById('message-input').value = '';
//     }
// }

// function sendPrivateMessage() {
//     var recipient = document.getElementById('recipient').value.trim();
//     sessionStorage.setItem('recipient', recipient);
//     var messageContent = document.getElementById('private-message-input').value.trim();
//     if (messageContent && recipient && stompClient) {
//         var chatMessage = {
//             message: messageContent,
//             senderName: username,
//             receiverName: recipient
//         };
//         stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
//         document.getElementById('private-message-input').value = '';
//         showMessage(chatMessage, 'private-messages');
//     }
// }

// function showMessage(message, elementId) {
//     var messageElement = document.createElement('p');
//     messageElement.textContent = message.senderName + ": " + message.message;
//     document.getElementById(elementId).appendChild(messageElement);
// }
var stompClient = null;
var useremail = localStorage.getItem('useremail') || null;
var receiveremail=localStorage.getItem('receiver');
var receivername=localStorage.getItem('receiverUserName');
console.log(receivername);
console.log(useremail);


function connect() {
    if (useremail) {
        var socket = new SockJS('http://127.0.0.1:8080/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            
            //document.getElementById('chat-room').style.display = 'block';
            document.getElementById('private-chat-room').style.display = 'block';
            // stompClient.subscribe('/chatroom/public', function(messageOutput) {
            //     showMessage(JSON.parse(messageOutput.body), 'messages');
            // });
            stompClient.subscribe('/user/' +  useremail+ '/private', function(messageOutput) {
                showMessage(JSON.parse(messageOutput.body), 'private-messages');
            });
        });
    }
}

// function sendMessage() {
//     var messageContent = document.getElementById('message-input').value.trim();
//     if (messageContent && stompClient) {
//         var chatMessage = {
//             message: messageContent,
//             senderName: username
//         };
//         stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
//         document.getElementById('message-input').value = '';
//     }
// }

function sendPrivateMessage() {
    var recipient =localStorage.getItem('receiver');
    var senderusername=localStorage.getItem('username');
    var receiverusername=localStorage.getItem('receiverUserName');
    // console.log('recipient',recipient);
    // console.log('senderusername',senderusername);
    // console.log('receiverusername',receiverusername);
    
    var messageContent = document.getElementById('private-message-input').value.trim();
    
    if (messageContent && recipient && stompClient) {
        var chatMessage = {
            message: messageContent,
            senderName: localStorage.getItem('username'),
            receiverName: recipient
            
        };
        

        stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
        document.getElementById('private-message-input').value = '';
        showMessage(chatMessage, 'private-messages');
    }
}


function showMessage(message, elementId) {
    var receiveremail = localStorage.getItem('receiver');
    var userName=localStorage.getItem('receiverUserName');
//    console.log('receiver',message.receiverName);
//    console.log(receiveremail);
    if ( receiveremail===message.receiverName) {
        var messageElement = document.createElement('p');
        messageElement.textContent = message.senderName + ": " + message.message;
        document.getElementById(elementId).appendChild(messageElement);
    }
    
     if(message.senderName === userName)
    {
        var messageElement = document.createElement('p');
        messageElement.textContent = message.senderName + ": " + message.message;
        document.getElementById(elementId).appendChild(messageElement);
    }
    else{
      MessageStore(message);

    }
    
}
async function MessageStore(message)
{
    const senderEmail=localStorage.getItem('useremail');
    const receiverEmail=localStorage.getItem('receiver');
    const senderUsername=localStorage.getItem('username');
    const receiverUserName=localStorage.getItem('receiverUserName');
    const usertoken=localStorage.getItem('usertoken');

     const formData=new FormData();
     formData.append('senderEmail',senderEmail);
     formData.append('receiverEmail',receiverEmail);
     formData.append('senderUsername',senderUsername);
     formData.append('receiverUsername',receiverUserName);
     formData.append('content',message.message);
     const response=await fetch('http://localhost:8080/pending/message/store',
         {
             method:"POST",
             headers: {
                'Authorization': `Bearer ${usertoken}`
            },
             body:formData,
         });
         
        if (!response.ok) {
            alert('Invalid');
        } 
}


connect();


function userProfile() {
    const receiverName = localStorage.getItem('receiverUserName');
    const receiverImage = localStorage.getItem('userprofilepicture');

    const profileDiv = document.getElementById('profile');

   
    var image = document.createElement("img"); 
    image.src = receiverImage;
    image.alt = "Profile Picture";
    image.style.width = "50px"; 
    image.style.height = "50px"; 
    image.style.borderRadius = "50%"; 

    var name = document.createElement("p");
    name.textContent = receiverName;

   
    profileDiv.appendChild(image);
    profileDiv.appendChild(name);
}

userProfile();



async function checkPendingMessage() {
    const receiverusername=localStorage.getItem('receiverUserName');
    const senderEmail = localStorage.getItem('receiver');
    const receiverEmail = localStorage.getItem('useremail');
    const usertoken=localStorage.getItem('usertoken');
    console.log('senderEmail:', senderEmail);
    console.log('receiverEmail:', receiverEmail);

    const formData = new FormData();
    formData.append('senderEmail', senderEmail);
    formData.append('receiverEmail', receiverEmail);

    const response = await fetch('http://localhost:8080/pending/message/check', {
        method: "POST",
        headers: {
            'Authorization': `Bearer ${usertoken}`
        },
        body: formData,
    });

    const pendingmessage = await response.json();
    console.log('pendingmessage:', pendingmessage);

    if (pendingmessage && pendingmessage.length > 0) {
        console.log('There are pending messages:', pendingmessage);

       
        const privateMessagesDiv = document.getElementById('private-messages');

       
        privateMessagesDiv.innerHTML = '';

       
        pendingmessage.forEach(message => {
            const messageElement = document.createElement('div');
            messageElement.textContent = receiverusername+ ": " + message.content;
            messageElement.style.color = 'green'; 
            privateMessagesDiv.appendChild(messageElement);
        });

    
        deleteMessage();
    } else {
        console.log('No pending messages found.');
    }
}

checkPendingMessage();


async function deleteMessage()
{
    const usertoken=localStorage.getItem('usertoken');
    const senderEmail=localStorage.getItem('receiver');
    const receiverEmail=localStorage.getItem('useremail');
    const formData=new FormData();
    formData.append('senderEmail',senderEmail);
    formData.append('receiverEmail',receiverEmail);
    const response=await fetch('http://localhost:8080/pending/message/delete',
        {
            method:"DELETE",
            headers: {
                'Authorization': `Bearer ${usertoken}`
            },
            body:formData,
        }
    );
    if(!response.ok)
    {
        alert('Error in temporary message deleting');
    }
}

