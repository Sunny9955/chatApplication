//package com.socalMedia.socalMedia.Config;
//
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;


//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class webSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        // Define the WebSocket endpoint that the client will use to connect to the server
//        registry.addEndpoint("/chat").setAllowedOrigins("http://127.0.0.1:5500").withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        // Enable a simple in-memory message broker and set the destination prefixes
//        config.enableSimpleBroker("/topic");
//        config.setApplicationDestinationPrefixes("/app");
//
//    }
//
//}

package com.socalMedia.socalMedia.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class webSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Define the WebSocket endpoint that the client will use to connect to the server
        registry.addEndpoint("/chat").setAllowedOrigins("http://127.0.0.1:5500").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Enable a simple in-memory message broker and set the destination prefixes
        config.enableSimpleBroker("/chatroom", "/user");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }
}

