package com.uiu.socialapp.socialapp.service;

import com.uiu.socialapp.socialapp.dto.PostResponse;
import com.uiu.socialapp.socialapp.exception.CustomException;
import com.uiu.socialapp.socialapp.model.Post;
import com.uiu.socialapp.socialapp.model.User;
import com.uiu.socialapp.socialapp.repository.PostRepository;
import com.uiu.socialapp.socialapp.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostResponse createPost(String content, String imageUrl, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("User not found"));

        Post post = new Post();
        post.setContent(content);
        post.setImageUrl(imageUrl);
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);

        Post savePost = postRepository.save(post);

        PostResponse response = new PostResponse();
        response.setId(savePost.getId());
        response.setContent(savePost.getContent());
        response.setImageUrl(savePost.getImageUrl());
        response.setCreateAt(savePost.getCreatedAt());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());

        return response;
    }
    public List<PostResponse> getAllPosts(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Post> postPage = postRepository.findAllByOrderByCreatedAtDesc(pageable);

        return postPage.getContent().stream().map(post -> {
            PostResponse response = new PostResponse();
            response.setId(post.getId());
            response.setContent(post.getContent());
            response.setImageUrl(post.getImageUrl());
            response.setUsername(post.getUser().getUsername());
            response.setEmail(post.getUser().getEmail());

            return response;
        }).toList();
    }
    public List<PostResponse> getAllPostsByUserEmail(String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Post> postPage = postRepository.findByUserEmailOrderByCreatedAtDesc(email, pageable);

        return postPage.getContent().stream().map(post -> {
            PostResponse response = new PostResponse();
            response.setId(post.getId());
            response.setContent(post.getContent());
            response.setImageUrl(post.getImageUrl());
            response.setUsername(post.getUser().getUsername());
            response.setEmail(post.getUser().getEmail());

            return response;
        }).toList();
    }
}