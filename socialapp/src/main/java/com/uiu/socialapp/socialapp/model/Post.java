package com.uiu.socialapp.socialapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;
}
