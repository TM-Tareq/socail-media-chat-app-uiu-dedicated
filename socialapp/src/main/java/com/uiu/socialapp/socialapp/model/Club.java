package com.uiu.socialapp.socialapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "clubs")
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    private String category;
    private String imageUrl;
    private LocalDateTime createAt;
}