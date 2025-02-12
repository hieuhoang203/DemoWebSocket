package com.example.demowebsocket.chatroom.service;

import com.example.demowebsocket.chatroom.entity.ChatRoom;
import com.example.demowebsocket.chatroom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository repository;

    public Optional<String> chatRoom (
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExist
    ) {
        return repository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom :: getChatId)
                .or(() -> {
                    if (createNewRoomIfNotExist) {
                        var chatRoomId = createChatRoomId(senderId, recipientId);
                        return Optional.of(chatRoomId);
                    }
                    return Optional.empty();
                });
    }

    private String createChatRoomId(String senderId, String recipientId) {
        var chatRoomId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderChatRoom = ChatRoom.builder()
                                    .id(UUID.randomUUID().toString())
                                    .senderId(senderId)
                                    .recipientId(recipientId)
                                    .build();

        repository.save(senderChatRoom);

        ChatRoom recipientChatRoom = ChatRoom.builder()
                                        .id(UUID.randomUUID().toString())
                                        .senderId(recipientId)
                                        .recipientId(senderId)
                                        .build();

        repository.save(recipientChatRoom);
        return chatRoomId;
    }

}
