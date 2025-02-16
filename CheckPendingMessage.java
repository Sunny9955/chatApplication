package com.socalMedia.socalMedia.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CheckPendingMessage {
    @Id
    @GeneratedValue
    private Long id;
    private String senderEmail;
    private String receiverEmail;
    private String content;
    private String senderUsername;
    private String receiverUsername;

}
