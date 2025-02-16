package com.socalMedia.socalMedia.Controller;

import com.socalMedia.socalMedia.Model.CheckPendingMessage;
import com.socalMedia.socalMedia.Service.CheckPendingMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckPendingMessageController {
    @Autowired
    private CheckPendingMessageService checkMessageService;

    @CrossOrigin(origins = ("http:localhost:5500"))
    @PostMapping("/pending/message/store")
    public void store(@RequestParam String senderEmail,@RequestParam String senderUsername,@RequestParam String receiverEmail,@RequestParam String receiverUsername,@RequestParam String content)
    {
        checkMessageService.storeMessage(senderEmail,senderUsername,receiverEmail,receiverUsername,content);
    }
    @CrossOrigin(origins=("http:localhost:5500"))
    @PostMapping("/pending/message/check")
    public List<CheckPendingMessage> check(@RequestParam String senderEmail, @RequestParam String receiverEmail){
       return checkMessageService.checkMessage(senderEmail,receiverEmail);
    }

    @CrossOrigin(origins = ("http:localhost:5500"))
    @DeleteMapping("/pending/message/delete")
    public void delete(@RequestParam String senderEmail,@RequestParam String receiverEmail)
    {
        checkMessageService.deleteMessage(senderEmail,receiverEmail);
    }

    @CrossOrigin(origins = ("http://localhost:5500"))
    @PostMapping("/pending/message/notification")
    public List<CheckPendingMessage>notification(@RequestParam String receiverEmail)
    {
        return checkMessageService.getNotification(receiverEmail);
    }

}
