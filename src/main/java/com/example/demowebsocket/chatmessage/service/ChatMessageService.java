package com.example.demowebsocket.chatmessage.service;

import com.example.demowebsocket.chatmessage.entity.ChatMessage;
import com.example.demowebsocket.chatmessage.repository.ChatMessageRepository;
import com.example.demowebsocket.chatroom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService.chatRoom(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                true
        ).orElseThrow();
        chatMessage.setId(UUID.randomUUID().toString());
        chatMessage.setChatId(chatId);
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findChatMessage(String senderId, String recipientId) {
        var chatId = chatRoomService.chatRoom(
                senderId,
                recipientId,
                true
        );
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }

}
