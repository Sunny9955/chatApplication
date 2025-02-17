package com.socalMedia.socalMedia.Service;

import com.socalMedia.socalMedia.Model.CheckPendingMessage;
import com.socalMedia.socalMedia.Repository.CheckPendingMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckPendingMessageService {
    @Autowired
    private CheckPendingMessageRepository checkMessageRepository;
    public void storeMessage(String senderEmail,String senderUsername,String receiverEmail,String receiverUsername,String content)
    {
        try {
            CheckPendingMessage checkMessage = CheckPendingMessage.builder()
                    .senderEmail(senderEmail)
                    .receiverEmail(receiverEmail)
                    .senderUsername(senderUsername)
                    .receiverUsername(receiverUsername)
                    .content(content)
                    .build();
            checkMessageRepository.save(checkMessage);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public List<CheckPendingMessage> checkMessage(String senderEmail, String receiverEmail)
    {
        return checkMessageRepository.findAllBySenderEmailAndReceiverEmail(senderEmail,receiverEmail);
    }
    public void deleteMessage(String senderEmail, String receiverEmail) {
        checkMessageRepository.deleteBySenderEmailAndReceiverEmail(senderEmail, receiverEmail);
    }

    public List<CheckPendingMessage>getNotification(String receiverEmail)
    {
        return checkMessageRepository.findAllByReceiverEmail(receiverEmail);
    }
}
