package com.uiu.socialapp.socialapp.dto;

import com.uiu.socialapp.socialapp.model.LostFoundStatus;
import lombok.Data;

@Data
public class LostFoundRequest {
    private String title;
    private String description;
    private String location;
    private String imageUrl;
    private LostFoundStatus status;
}
