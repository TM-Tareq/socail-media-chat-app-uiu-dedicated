package com.uiu.socialapp.socialapp.dto;

import lombok.Data;

@Data
public class ClubRequest {
    private String clubName;
    private String description;
    private String imageUrl;
    private String category;
}
