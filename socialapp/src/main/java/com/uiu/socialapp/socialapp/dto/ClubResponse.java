package com.uiu.socialapp.socialapp.dto;

import jdk.jfr.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClubResponse {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String imageUrl;
    private LocalDateTime createdAt;
    private String createdByUsername;
    private int memberCount;
}
