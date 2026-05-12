package com.uiu.socialapp.socialapp.service;

import com.uiu.socialapp.socialapp.exception.CustomException;
import com.uiu.socialapp.socialapp.model.Follow;
import com.uiu.socialapp.socialapp.model.User;
import com.uiu.socialapp.socialapp.repository.FollowRepository;
import com.uiu.socialapp.socialapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public String toggleFollow(Long targetUserId, String followerEmail) {
        User follower = userRepository.findByEmail(followerEmail).orElseThrow(()-> new CustomException("User not found"));

        User following = userRepository.findById(targetUserId).orElseThrow(()-> new CustomException("User not found"));

        boolean alreadyFollowed = followRepository.existsByFollowerAndFollowing(follower,following);
        Follow follow = new Follow();
        follow.setFollowing(following);
        follow.setFollower(follower);

        if(alreadyFollowed){
            Follow existingFollowed = followRepository.findByFollowerAndFollowing(follower,following);
            followRepository.delete(existingFollowed);
            return "Unfollowed";
        } else {
            followRepository.save(follow);
            return "Followed";
        }
    }
}
