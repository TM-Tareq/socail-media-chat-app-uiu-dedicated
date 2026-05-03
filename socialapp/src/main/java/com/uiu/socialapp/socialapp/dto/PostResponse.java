package com.uiu.socialapp.socialapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private Long id;
    private String content;
    private String imageUrl;
    private LocalDateTime createAt;

    private String username;
    private String email;
}
