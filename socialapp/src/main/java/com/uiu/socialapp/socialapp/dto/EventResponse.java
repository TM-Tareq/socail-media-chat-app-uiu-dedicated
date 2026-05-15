package com.uiu.socialapp.socialapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String createdByUsername;
    private int attendeeCount;
}
