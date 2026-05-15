package com.uiu.socialapp.socialapp.dto;

import com.uiu.socialapp.socialapp.model.LostFoundStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LostFoundResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String imageUrl;
    private LostFoundStatus status;
    private String createdByUsername;
    private LocalDateTime createdAt;
}
