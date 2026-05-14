package com.uiu.socialapp.socialapp.repository;

import com.uiu.socialapp.socialapp.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {
    List<Club>findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name, String category);
}
