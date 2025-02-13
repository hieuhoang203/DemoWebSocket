package com.example.demowebsocket.chatmessage.controller;

import com.example.demowebsocket.chatmessage.entity.ChatMessage;
import com.example.demowebsocket.chatmessage.entity.ChatNotification;
import com.example.demowebsocket.chatmessage.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;

    private final ChatMessageService chatMessageService;

    @MessageMapping(value = "/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatMessage savedChatMessage = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),
                "/queue/messages",
                ChatNotification.builder()
                        .id(savedChatMessage.getId())
                        .senderId(chatMessage.getSenderId())
                        .recipientId(chatMessage.getRecipientId())
                        .message(chatMessage.getMessage())
                        .build()
        );
    }

    @GetMapping(value = "/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessage(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId
    ) {
        return ResponseEntity.ok(chatMessageService.findChatMessage(senderId, recipientId));
    }

}
