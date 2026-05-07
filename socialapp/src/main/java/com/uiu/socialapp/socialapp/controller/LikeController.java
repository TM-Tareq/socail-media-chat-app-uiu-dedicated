package com.uiu.socialapp.socialapp.controller;

import com.uiu.socialapp.socialapp.dto.CreatePostRequest;
import com.uiu.socialapp.socialapp.model.Like;
import com.uiu.socialapp.socialapp.service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    private final LikeService likeService;

    LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @PostMapping("/{postId}")
    public String createLike(@PathVariable Long postId, HttpServletRequest request){
        String email = (String) request.getAttribute("userEmail");
        return likeService.toggleLike(postId,email);
    }
}
