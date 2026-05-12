package com.uiu.socialapp.socialapp.repository;

import com.uiu.socialapp.socialapp.model.Follow;
import com.uiu.socialapp.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerAndFollowing(User follower, User following);

    int countByFollowing(User follower);

    int countByFollower(User follower);

    Follow findByFollowerAndFollowing(User follower, User following);
}
