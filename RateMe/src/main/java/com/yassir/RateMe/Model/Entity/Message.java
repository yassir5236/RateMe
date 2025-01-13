package com.yassir.RateMe.Model.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name="Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;
    private Long receiverId;
    private String content;
    private LocalDateTime timestamp;

}
