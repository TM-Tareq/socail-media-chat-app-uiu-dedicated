package com.uiu.socialapp.socialapp.controller;

import com.uiu.socialapp.socialapp.dto.CommentResponse;
import com.uiu.socialapp.socialapp.model.Comment;
import com.uiu.socialapp.socialapp.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public CommentResponse addComment(@PathVariable Long postId, @RequestBody Map<String, String> body, HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        String content = body.get("content");

        return commentService.addComment(postId, email, content);
    }

    @GetMapping("/{postId}")
    public List<CommentResponse> getComments(@PathVariable Long postId, HttpServletRequest request) {
        return commentService.getCommentsByPostId(postId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId, HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        commentService.deleteComment(commentId, email);
    }
}
