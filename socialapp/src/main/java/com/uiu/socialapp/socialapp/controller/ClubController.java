package com.uiu.socialapp.socialapp.controller;

import com.uiu.socialapp.socialapp.dto.ClubRequest;
import com.uiu.socialapp.socialapp.dto.ClubResponse;
import com.uiu.socialapp.socialapp.service.ClubService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {
    private final ClubService clubService;
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping()
    public ClubResponse createClub(@RequestBody ClubRequest clubRequest, HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        return clubService.createClub(clubRequest.getClubName(), clubRequest.getDescription(), clubRequest.getCategory(), clubRequest.getImageUrl(), email);
    }

    @PostMapping("/{clubId}/join")
    public String joinOrLeave(@PathVariable Long clubId, HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        return clubService.toggleJoinOrLeave(clubId, email);
    }

    @GetMapping
    public List<ClubResponse> getAllClubs() {
        return clubService.getAllClubs();
    }

    @GetMapping("/me")
    public List<ClubResponse> getMyClubs(HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        return clubService.getMyClubs(email);
    }
}
