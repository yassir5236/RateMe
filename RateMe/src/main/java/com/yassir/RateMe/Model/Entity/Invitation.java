package com.yassir.RateMe.Model.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Invitations")
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;
    private Long receiverId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime sentDate;

    public enum Status {
        PENDING, ACCEPTED, REJECTED
    }

}

