package com.uiu.socialapp.socialapp.repository;

import com.uiu.socialapp.socialapp.model.Club;
import com.uiu.socialapp.socialapp.model.ClubMember;
import com.uiu.socialapp.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember,Long> {
    List<ClubMember>findByUser(User user);
    List<ClubMember>findByClub(Club club);
    ClubMember findByUserAndClub(User user,Club club);
    boolean existsByUserAndClub(User user, Club club);
}