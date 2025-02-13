package com.example.demowebsocket.user.controller;

import com.example.demowebsocket.constant.Status;
import com.example.demowebsocket.user.entity.User;
import com.example.demowebsocket.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @MessageMapping(value = "/user.addUser")
    @SendTo(value = "/user/")
    public User addUser(User user) {
        service.saveUser(user);
        return user;
    }

    @MessageMapping(value = "/disconnect.disconnectUser")
    @SendTo(value = "/user/topic")
    public User disconnect (@Payload User user) {
        user.setStatus(Status.OFFLINE);
        service.saveUser(user);
        return user;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(service.findConnectedUsers());
    }

}
