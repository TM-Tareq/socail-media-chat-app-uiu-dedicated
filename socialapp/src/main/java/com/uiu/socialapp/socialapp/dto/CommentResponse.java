package com.uiu.socialapp.socialapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private long id;
    private String content;
    private LocalDateTime createTime;
    private String username;
    private String email;
}
