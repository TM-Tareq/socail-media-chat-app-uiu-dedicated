package com.uiu.socialapp.socialapp.service;

import com.uiu.socialapp.socialapp.dto.LostFoundRequest;
import com.uiu.socialapp.socialapp.dto.LostFoundResponse;
import com.uiu.socialapp.socialapp.exception.CustomException;
import com.uiu.socialapp.socialapp.model.LostFound;
import com.uiu.socialapp.socialapp.model.LostFoundStatus;
import com.uiu.socialapp.socialapp.model.User;
import com.uiu.socialapp.socialapp.repository.LostFoundRepository;
import com.uiu.socialapp.socialapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LostFoundService {
    private final LostFoundRepository lostFoundRepository;
    private final UserRepository userRepository;

    public LostFoundService(LostFoundRepository lostFoundRepository, UserRepository userRepository) {
        this.lostFoundRepository = lostFoundRepository;
        this.userRepository = userRepository;
    }

    public LostFoundResponse createLostFound(LostFoundRequest request, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException("User not found"));

        LostFound lostFound = new LostFound();
        lostFound.setTitle(request.getTitle());
        lostFound.setDescription(request.getDescription());
        lostFound.setLocation(request.getLocation());
        lostFound.setImageUrl(request.getImageUrl());
        lostFound.setCreatedBy(user);
        lostFound.setStatus(request.getStatus());

        LostFound savedLostFound = lostFoundRepository.save(lostFound);

        LostFoundResponse response = new LostFoundResponse();

        response.setId(lostFound.getId());
        response.setTitle(lostFound.getTitle());
        response.setDescription(lostFound.getDescription());
        response.setLocation(lostFound.getLocation());
        response.setImageUrl(lostFound.getImageUrl());
        response.setCreatedByUsername(lostFound.getCreatedBy().getUsername());
        response.setStatus(savedLostFound.getStatus());
        response.setCreatedAt(LocalDateTime.now());

        return response;
    }

    public List<LostFoundResponse> getAllItems() {
        return lostFoundRepository.findAll().stream().map(item -> {
            LostFoundResponse response = new LostFoundResponse();
            response.setId(item.getId());
            response.setTitle(item.getTitle());
            response.setDescription(item.getDescription());
            response.setLocation(item.getLocation());
            response.setImageUrl(item.getImageUrl());
            response.setCreatedByUsername(item.getCreatedBy().getUsername());
            response.setCreatedAt(item.getCreatedAt());
            response.setStatus(item.getStatus());

            return response;
        }).toList();
    }

    public List<LostFoundResponse> getItemsByStatus(LostFoundStatus status) {
        return lostFoundRepository.findByStatus(status).stream().map(item -> {
            LostFoundResponse response = new LostFoundResponse();
            response.setId(item.getId());
            response.setTitle(item.getTitle());
            response.setDescription(item.getDescription());
            response.setLocation(item.getLocation());
            response.setImageUrl(item.getImageUrl());
            response.setCreatedByUsername(item.getCreatedBy().getUsername());
            response.setCreatedAt(item.getCreatedAt());
            response.setStatus(item.getStatus());

            return response;
        }).toList();
    }

    public List<LostFoundResponse> getMyPostItems(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException("User not found"));

        return lostFoundRepository.findByCreatedBy(user).stream().map(item -> {
            LostFoundResponse response = new LostFoundResponse();
            response.setId(item.getId());
            response.setTitle(item.getTitle());
            response.setDescription(item.getDescription());
            response.setLocation(item.getLocation());
            response.setImageUrl(item.getImageUrl());
            response.setCreatedByUsername(item.getCreatedBy().getUsername());
            response.setCreatedAt(item.getCreatedAt());
            response.setStatus(item.getStatus());

            return response;
        }).toList();
    }
}
