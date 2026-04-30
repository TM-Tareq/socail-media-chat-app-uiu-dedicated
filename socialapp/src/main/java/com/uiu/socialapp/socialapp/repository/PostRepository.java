package com.uiu.socialapp.socialapp.repository;

import com.uiu.socialapp.socialapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {



}
