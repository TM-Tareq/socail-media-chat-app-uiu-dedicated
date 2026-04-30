package com.uiu.socialapp.socialapp.service;

import com.uiu.socialapp.socialapp.dto.RegisterRequest;
import com.uiu.socialapp.socialapp.exception.CustomException;
import com.uiu.socialapp.socialapp.model.Role;
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

        if(request.getUniversityId() == null || !request.getUniversityId().startsWith("011") || request.getUniversityId().length() != 10)
            throw new CustomException("Id section must be fillup and starts with 011");

        if(userRepository.existsByUniversityId(request.getUniversityId()))
            throw new CustomException("This Id already exists");

        if (request.getName() == null || request.getName().isBlank())
            throw new CustomException("Name section must fill up");

        if(request.getUsername() == null || request.getUsername().isBlank())
            throw new CustomException("Username must be fillup");

        if(userRepository.existsByUsername(request.getUsername()))
            throw new CustomException("This username already exists");

        if(request.getEmail() == null || !request.getEmail().endsWith("@bscse.uiu.ac.bd"))
            throw new CustomException("Email must end with @bscse.uiu.ac.bd");

        if(userRepository.existsByEmail(request.getEmail()))
            throw new CustomException("This email already exists");

        if(request.getPassword() == null || request.getPassword().length() < 6)
            throw new CustomException("Password must contains at least 6 characters");

        User user = new User();
        user.setUniversityId(request.getUniversityId());
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        String hashedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        user.setPassword(hashedPassword);

        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found"));
    }

    public User updateUser(Long id, User newUser, String loggedInEmail) {
        User existUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found"));

        if(!existUser.getEmail().equals(loggedInEmail)) {
            throw new CustomException("You are not allowed to update this user's profile!");
        }

        if(newUser.getUsername() != null && !newUser.getUsername().equals(existUser.getUsername())) {
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

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found"));
        userRepository.delete(user);
    }

    public String loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("User not found"));

        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new CustomException("Invalid password");
        }

        return jwtService.generateToken(user.getEmail());
    }
}