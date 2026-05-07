package com.uiu.socialapp.socialapp.repository;

import com.uiu.socialapp.socialapp.model.Like;
import com.uiu.socialapp.socialapp.model.Post;
import com.uiu.socialapp.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    int countByPost(Post post);
    Like findByUserAndPost(User user, Post post);

    boolean existsByUserAndPost(User user, Post post);
}
