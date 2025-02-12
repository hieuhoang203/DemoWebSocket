package com.example.demowebsocket.service;

import com.example.demowebsocket.constant.Status;
import com.example.demowebsocket.entity.User;
import com.example.demowebsocket.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnectedUser(User user) {
        var storedUser = repository.findById(user.getNickname()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers () {
        return repository.findAllByStatus(Status.ONLINE);
    }

}
