package com.example.demowebsocket.chatroom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chatRoom")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChatRoom {

    @Id
    private String id;

    private String chatId;

    private String senderId;

    private String recipientId;

}
