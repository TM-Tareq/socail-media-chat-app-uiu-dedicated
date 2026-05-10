package com.uiu.socialapp.socialapp.service;

import com.uiu.socialapp.socialapp.exception.CustomException;
import com.uiu.socialapp.socialapp.model.Comment;
import com.uiu.socialapp.socialapp.model.Post;
import com.uiu.socialapp.socialapp.model.User;
import com.uiu.socialapp.socialapp.repository.CommentRepository;
import com.uiu.socialapp.socialapp.repository.PostRepository;
import com.uiu.socialapp.socialapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    CommentService (UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Long postId, String email, String commentContent) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new CustomException("User not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new CustomException("Post not found"));

        Comment comment = new Comment();

        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(commentContent);
        comment.setCreateTime(LocalDateTime.now());
        commentRepository.save(comment);

        return comment;
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException("Post not found"));
        return commentRepository.findAllByPost(post);
    }

    public void deleteComment(Long commentId, String email) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new CustomException("Comment not found"));
        if(!comment.getUser().getEmail().equals(email)) {
            throw new CustomException("You cannot delete this comment");
        }
        commentRepository.delete(comment);
    }
}
