package com.uiu.socialapp.socialapp;

import com.uiu.socialapp.socialapp.dto.RegisterRequest;
import com.uiu.socialapp.socialapp.repository.UserRepository;
import com.uiu.socialapp.socialapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserService userService;
    private final UserRepository userRepository;

    public DataLoader(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(!userRepository.existsByUsername("daniel")) {

            RegisterRequest request = new RegisterRequest();
            request.setUniversityId("0111111111");
            request.setName("Daniel Gregor");
            request.setUsername("daniel");
            request.setEmail("daniel@bscse.uiu.ac.bd");
            request.setPassword("mypassword");

            userService.registerUser(request);

            System.out.println("User inserted successfully!");
        }
    }
}
