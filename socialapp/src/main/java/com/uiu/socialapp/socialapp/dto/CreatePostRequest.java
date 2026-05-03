package com.uiu.socialapp.socialapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePostRequest {
    private String content;
    private String imageUrl;
}
