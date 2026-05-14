package com.uiu.socialapp.socialapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "event_attendee",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "event_id"}
        )
)
public class EventAttendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Enumerated(EnumType.STRING)
    private AttendeeStatus status;
}
