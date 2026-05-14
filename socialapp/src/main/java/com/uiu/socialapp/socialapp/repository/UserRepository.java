package com.uiu.socialapp.socialapp.repository;

import com.uiu.socialapp.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    Checking duplicate data(so fast)
    boolean existsByUniversityId(String universityId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsById(Long id);

    List<User> findByUsernameContainingIgnoreCase(String username);


//    For searching out data(Used for relief from NullPointerException)
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUniversityId(String universityId);
}


// save() -> insert and update, findId()-> get one, findAll() -> get all, delete() -> remove