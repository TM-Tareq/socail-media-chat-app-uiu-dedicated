package com.uiu.socialapp.socialapp.repository;

import com.uiu.socialapp.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

//    Checking duplicate data(so fast)
    boolean existByUniversityId(String universityId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsById(String id);


//    For searching out data(Used for relief from NullPointerException)
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUniversityId(String universityId);
}


// save() -> insert and update, findId()-> get one, findAll() -> get all, delete() -> remove