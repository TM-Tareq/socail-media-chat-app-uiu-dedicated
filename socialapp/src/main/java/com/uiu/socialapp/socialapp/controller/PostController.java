package com.uiu.socialapp.socialapp.controller;

import com.uiu.socialapp.socialapp.dto.CreatePostRequest;
import com.uiu.socialapp.socialapp.dto.PostResponse;
import com.uiu.socialapp.socialapp.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<PostResponse> getPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return postService.getAllPosts(page, size);
    }

    @GetMapping("/me")
    public List<PostResponse> getMyPosts(HttpServletRequest request, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        String email = request.getAttribute("userEmail").toString();
        return postService.getAllPostsByUserEmail(email, page, size);
    }
}