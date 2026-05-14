package com.uiu.socialapp.socialapp.service;

import com.uiu.socialapp.socialapp.dto.PostResponse;
import com.uiu.socialapp.socialapp.dto.UserResponse;
import com.uiu.socialapp.socialapp.repository.PostRepository;
import com.uiu.socialapp.socialapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SearchService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    SearchService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<UserResponse> searchUsers(String query) {
        return userRepository.findByUsernameContainingIgnoreCase(query)
                .stream().map(user -> {
                    UserResponse response = new UserResponse();
                    response.setId(user.getId());
                    response.setName(user.getName());
                    response.setUsername(user.getUsername());
                    response.setEmail(user.getEmail());

                    return response;
                }).toList();
    }

    public List<PostResponse>  searchPosts(String query) {
        return postRepository.findByContentContainingIgnoreCase(query)
                .stream().map(user-> {
                    PostResponse response = new PostResponse();

                    response.setId(user.getId());
                    response.setUsername(user.getUser().getUsername());
                    response.setImageUrl(user.getImageUrl());
                    response.setContent(user.getContent());
                    response.setEmail(user.getUser().getEmail());
                    response.setCreateAt(user.getCreatedAt());

                    return response;
                }).toList();

    }
}
