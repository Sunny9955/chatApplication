package com.socalMedia.socalMedia.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Message {
//    private String from;
//    private String text;
//
//}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message{
    private String senderName;
    private String receiverName;
    private String message;



}
