//package com.socalMedia.socalMedia.Controller;
//
//import com.socalMedia.socalMedia.Model.Message;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//@Controller
//public class ChatController {
//    @CrossOrigin(origins = "http://localhost:5500")
//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public Message sendMessage(Message message) {
//        return message;
//    }
//}

package com.socalMedia.socalMedia.Controller;

import com.socalMedia.socalMedia.Model.Message;
import com.socalMedia.socalMedia.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @CrossOrigin(origins = "http://localhost:5500")
    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message sendMessage(@Payload  Message message) {
        return message;
    }

    @MessageMapping("/private-message")
    public Message receiverPrivateMessage(@Payload Message message)
    {

        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        return message;
    }
}