package com.uiu.socialapp.socialapp.controller;
import com.uiu.socialapp.socialapp.service.FollowService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follows")
public class FollowController {
    private final FollowService followService;

    FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/{targetUserId}")
    public String follow(@PathVariable("targetUserId") Long targetUserId, HttpServletRequest request) {
        String email =  (String) request.getAttribute("userEmail");
        return followService.toggleFollow(targetUserId,email);
    }
}
