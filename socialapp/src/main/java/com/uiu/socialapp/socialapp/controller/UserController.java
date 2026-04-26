package com.uiu.socialapp.socialapp.controller;

import com.uiu.socialapp.socialapp.dto.LoginRequest;
import com.uiu.socialapp.socialapp.dto.RegisterRequest;
import com.uiu.socialapp.socialapp.model.User;
import com.uiu.socialapp.socialapp.service.UserService;
import jakarta.persistence.PostUpdate;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User postUser(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

//  Getting All
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

//  Getting by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

//  Update by ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user, HttpServletRequest request) {
        String loggedInEmail = (String) request.getAttribute("userEmail");
        return userService.updateUser(id, user,  loggedInEmail);
    }

//  Delete by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

//  Login
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
    }

//  Jwt token accept
    @GetMapping("/me")
    public String getCurrentUserEmail() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || !auth.isAuthenticated()) {
            return "Unauthorized";
        }
        return auth.getPrincipal().toString();
    }
}