package com.example.demowebsocket.repositories;

import com.example.demowebsocket.constant.Status;
import com.example.demowebsocket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
