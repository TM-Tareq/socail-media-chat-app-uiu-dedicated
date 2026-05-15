package com.uiu.socialapp.socialapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "Lost_found")
@Entity
public class LostFound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String location;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private LostFoundStatus status;
}
