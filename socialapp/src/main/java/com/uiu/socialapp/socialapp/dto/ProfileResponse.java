package com.uiu.socialapp.socialapp.dto;
import lombok.Data;

import java.util.List;

@Data
public class ProfileResponse {
    private String name;
    private String username;
    private String email;
    private int followersCount;
    private int followingCount;
    private List<PostResponse> posts;
}