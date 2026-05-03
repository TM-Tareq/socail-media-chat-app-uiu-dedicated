package com.uiu.socialapp.socialapp.controller;

import com.uiu.socialapp.socialapp.dto.CreatePostRequest;
import com.uiu.socialapp.socialapp.dto.PostResponse;
import com.uiu.socialapp.socialapp.model.Post;
import com.uiu.socialapp.socialapp.service.PostService;
import com.uiu.socialapp.socialapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResponse createPost(@RequestBody CreatePostRequest request, HttpServletRequest httprequest) {
        String email = (String) httprequest.getAttribute("userEmail");

        return postService.createPost(request.getContent(), request.getImageUrl(), email);
    }
}
