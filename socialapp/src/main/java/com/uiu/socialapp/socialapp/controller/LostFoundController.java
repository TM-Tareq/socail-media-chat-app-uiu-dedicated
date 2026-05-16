package com.uiu.socialapp.socialapp.controller;

import com.uiu.socialapp.socialapp.dto.LostFoundRequest;
import com.uiu.socialapp.socialapp.dto.LostFoundResponse;
import com.uiu.socialapp.socialapp.model.LostFoundStatus;
import com.uiu.socialapp.socialapp.service.LostFoundService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lostFound")
public class LostFoundController {
    private final LostFoundService lostFoundService;

    public LostFoundController(LostFoundService lostFoundService) {
        this.lostFoundService = lostFoundService;
    }

    @PostMapping
    public LostFoundResponse createLostFound(@RequestBody LostFoundRequest lostFoundRequest, HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");

        return lostFoundService.createLostFound(lostFoundRequest, email);
    }

    @GetMapping
    public List<LostFoundResponse> getAllItems() {
        return lostFoundService.getAllItems();
    }

    @GetMapping("/status")
    public List<LostFoundResponse> getItemsByStatus(@RequestParam LostFoundStatus status) {
        return lostFoundService.getItemsByStatus(status);
    }

    @GetMapping("/me")
    public List<LostFoundResponse> getMyItems(HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        return lostFoundService.getMyPostItems(email);
    }
}
