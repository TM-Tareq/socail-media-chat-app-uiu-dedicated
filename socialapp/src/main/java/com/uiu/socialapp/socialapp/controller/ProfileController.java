package com.uiu.socialapp.socialapp.controller;
import com.uiu.socialapp.socialapp.dto.ProfileResponse;
import com.uiu.socialapp.socialapp.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/me")
    public ProfileResponse getProfile(HttpServletRequest request){
        String email = (String) request.getAttribute("userEmail");
        return profileService.getProfile(email);
    }
    @GetMapping("/{userId}")
    public ProfileResponse getUserProfile(@PathVariable Long userId) {
        return profileService.getProfileById(userId);
    }


}
