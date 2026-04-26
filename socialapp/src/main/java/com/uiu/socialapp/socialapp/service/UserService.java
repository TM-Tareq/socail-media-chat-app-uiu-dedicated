package com.uiu.socialapp.socialapp.service;

import com.uiu.socialapp.socialapp.dto.RegisterRequest;
import com.uiu.socialapp.socialapp.exception.CustomException;
import com.uiu.socialapp.socialapp.model.User;
import com.uiu.socialapp.socialapp.repository.UserRepository;
import com.uiu.socialapp.socialapp.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
    }

    public User registerUser(RegisterRequest request) {

        // id, name, username
        if(request.getUniversityId() == null || !request.getUniversityId().startsWith("011") || request.getUniversityId().length() < 10) throw new CustomException("Id section must be fillup and starts with 011");
        if(userRepository.findById(request.getUniversityId()).isPresent()) throw new CustomException("This Id already exists");

        if (request.getName() == null || request.getName().isBlank()) throw new CustomException("Name section must fill up");

        if(request.getUsername() == null || request.getUsername().isBlank()) throw new CustomException("Username must be fillup");
        if(userRepository.findByUsername(request.getUsername()).isPresent()) throw new CustomException("This username already exists");

        //    Email validation
        if(request.getEmail() == null || !request.getEmail().endsWith("@bscse.uiu.ac.bd")) throw new CustomException("Email must end with @bscse.uiu.ac.bd");
        if(userRepository.findByEmail(request.getEmail()).isPresent()) throw new CustomException("This email already exists");

        //    Password validation
        if(request.getPassword() == null || request.getPassword().length() < 6) {
            throw new CustomException("Password must contains at least 6 characters");
        }

        User user = new User();
        user.setUniversityId(request.getUniversityId());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        String hashedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        user.setPassword(hashedPassword);

        user.setRole(com.uiu.socialapp.socialapp.model.Role.USER);

        //    Save
        return userRepository.save(user); // store and return does both
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    };

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new CustomException("User not found"));

    }

    public User updateUser(String id, User newUser, String loggedInEmail) {
        User existUser = userRepository.findById(id).orElseThrow(()-> new CustomException("User not found"));

        if(!existUser.getEmail().equals(loggedInEmail)) {
            throw new CustomException("You are not allowed to update this user's profile!");
        }

        if(newUser.getUsername() != null && !newUser.getUsername().equals((existUser.getUsername()))) {
            if(userRepository.existsByUsername(newUser.getUsername())) {
                throw new CustomException("This username already exists");
            }
            existUser.setUsername(newUser.getUsername());
        }
        if(newUser.getPassword() != null && !newUser.getPassword().isBlank()) {
            existUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        }

        return userRepository.save(existUser);
    }

    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new CustomException("User not found"));
        userRepository.delete(user);
    }

    public String loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("User not found"));
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new CustomException("Invalid password");
        }
        String token = jwtService.generateToken(user.getEmail());
        return token;
    }
}
