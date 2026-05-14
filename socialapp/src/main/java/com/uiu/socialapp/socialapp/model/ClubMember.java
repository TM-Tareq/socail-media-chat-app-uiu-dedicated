package com.uiu.socialapp.socialapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "Club_Member",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "club_id"}
        )
)
@Entity
public class ClubMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
    private LocalDateTime joinedAt;
}
