package com.uiu.socialapp.socialapp.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String universityId;
    private String name;
    private String username;
    private String email;
    private String password;
}
