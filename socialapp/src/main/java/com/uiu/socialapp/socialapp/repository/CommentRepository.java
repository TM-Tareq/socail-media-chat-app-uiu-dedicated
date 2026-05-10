package com.uiu.socialapp.socialapp.repository;

import com.uiu.socialapp.socialapp.model.Comment;
import com.uiu.socialapp.socialapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}