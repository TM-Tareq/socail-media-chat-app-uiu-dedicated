package com.uiu.socialapp.socialapp.service;
import com.uiu.socialapp.socialapp.dto.PostResponse;
import com.uiu.socialapp.socialapp.dto.ProfileResponse;
import com.uiu.socialapp.socialapp.exception.CustomException;
import com.uiu.socialapp.socialapp.model.User;
import com.uiu.socialapp.socialapp.repository.FollowRepository;
import com.uiu.socialapp.socialapp.repository.PostRepository;
import com.uiu.socialapp.socialapp.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final PostRepository postRepository;

    ProfileService (UserRepository userRepository, FollowRepository followRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.postRepository = postRepository;
    }

    public ProfileResponse getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("User not found"));

        int followersCount = followRepository.countByFollowing(user);
        int followingCount = followRepository.countByFollower(user);

        List<PostResponse> posts = postRepository.findByUserEmailOrderByCreatedAtDesc(email, PageRequest.of(0, 10))
                .getContent().stream().map(post -> {
                    PostResponse response = new PostResponse();
                    response.setId(post.getId());
                    response.setContent(post.getContent());
                    response.setImageUrl(post.getImageUrl());
                    response.setUsername(user.getUsername());
                    response.setEmail(user.getEmail());
                    return response;
                }).toList();

        ProfileResponse profile = new ProfileResponse();
        profile.setName(user.getName());
        profile.setUsername(user.getUsername());
        profile.setEmail(user.getEmail());
        profile.setFollowersCount(followersCount);
        profile.setFollowingCount(followingCount);
        profile.setPosts(posts);

        return profile;
    }

    public ProfileResponse getProfileById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found"));
        return getProfile(user.getEmail());
    }
}