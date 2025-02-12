package com.example.demowebsocket.controller;

import com.example.demowebsocket.entity.User;
import com.example.demowebsocket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @MessageMapping("/user.addUser")
    @SendTo("/user/")
    public User addUser(User user) {
        service.saveUser(user);
        return user;
    }



}
