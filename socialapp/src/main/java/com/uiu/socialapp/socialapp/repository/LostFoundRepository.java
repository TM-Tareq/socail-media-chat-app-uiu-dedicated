package com.uiu.socialapp.socialapp.repository;

import com.uiu.socialapp.socialapp.model.LostFound;
import com.uiu.socialapp.socialapp.model.LostFoundStatus;
import com.uiu.socialapp.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostFoundRepository extends JpaRepository<LostFound, Long> {
    List<LostFound> findByStatus(LostFoundStatus status);
    List<LostFound> findByCreatedBy(User user);
}
