package com.uiu.socialapp.socialapp.service;

import com.uiu.socialapp.socialapp.exception.CustomException;
import com.uiu.socialapp.socialapp.model.Like;
import com.uiu.socialapp.socialapp.model.Post;
import com.uiu.socialapp.socialapp.model.User;
import com.uiu.socialapp.socialapp.repository.LikeRepository;
import com.uiu.socialapp.socialapp.repository.PostRepository;
import com.uiu.socialapp.socialapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public LikeService(LikeRepository likeRepository, PostRepository postRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public String toggleLike(Long postId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException("User not found"));
        Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException("Post not found"));

        boolean alreadyLiked = likeRepository.existsByUserAndPost(user,post);

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);

        if(alreadyLiked){
            Like existingLike = likeRepository.findByUserAndPost(user, post);
            likeRepository.delete(existingLike);
            return "Unliked";
        } else {
            likeRepository.save(like);
            return "Liked";
        }
    }

    public int getLikeCount(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException("Post not found"));

        return likeRepository.countByPost(post);
    }
}