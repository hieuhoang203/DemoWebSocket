package com.example.demowebsocket.entity;

import com.example.demowebsocket.constant.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class User {

    @Id
    private String nickname;

    private String fullname;

    private Status status;

}
