package com.uiu.socialapp.socialapp.dto;


import lombok.Data;

// Encapsulation confirmed by this
@Data
public class LoginRequest {
    private String email;
    private String password;
}

