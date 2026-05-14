package com.uiu.socialapp.socialapp.service;

import com.uiu.socialapp.socialapp.dto.ClubResponse;
import com.uiu.socialapp.socialapp.exception.CustomException;
import com.uiu.socialapp.socialapp.model.Club;
import com.uiu.socialapp.socialapp.model.ClubMember;
import com.uiu.socialapp.socialapp.model.User;
import com.uiu.socialapp.socialapp.repository.ClubMemberRepository;
import com.uiu.socialapp.socialapp.repository.ClubRepository;
import com.uiu.socialapp.socialapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private final ClubMemberRepository clubMemberRepository;
    private final UserRepository userRepository;

    public ClubService(ClubRepository clubRepository, ClubMemberRepository clubMemberRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.clubMemberRepository = clubMemberRepository;
        this.userRepository = userRepository;
    }

    public ClubResponse createClub(String name, String description, String Category, String imageUrl, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException("User not found"));

        Club club = new Club();
        club.setName(name);
        club.setDescription(description);
        club.setCategory(Category);
        club.setImageUrl(imageUrl);
        club.setCreatedBy(user);
        club.setCreateAt(LocalDateTime.now());

        Club savedClub = clubRepository.save(club);

        ClubResponse  response = new ClubResponse();

        response.setId(savedClub.getId());
        response.setName(savedClub.getName());
        response.setDescription(savedClub.getDescription());
        response.setCategory(savedClub.getCategory());
        response.setImageUrl(savedClub.getImageUrl());
        response.setCreatedAt(savedClub.getCreateAt());
        response.setCreatedByUsername(user.getUsername());
        response.setMemberCount(0);

        return response;
    }

//    joinOrLeaveClub()
    public String toggleJoinOrLeave(Long clubId, String email) {
        Club club = clubRepository.findById(clubId).orElseThrow(()-> new CustomException("Club not found"));
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException("User not found"));

        boolean alreadyJoined = clubMemberRepository.existsByUserAndClub(user,club);

        if(alreadyJoined){
            ClubMember existingMember = clubMemberRepository.findByUserAndClub(user, club);
            clubMemberRepository.delete(existingMember);
            return "Member left";
        } else {
            ClubMember newMember = new ClubMember();
            newMember.setUser(user);
            newMember.setClub(club);
            newMember.setJoinedAt(LocalDateTime.now());
            clubMemberRepository.save(newMember);

            return "New Member Joined";
        }
    }

    public List<ClubResponse> getAllClubs() {
        return clubRepository.findAll().stream().map((club)-> {
            ClubResponse response = new ClubResponse();
            response.setId(club.getId());
            response.setName(club.getName());
            response.setDescription(club.getDescription());
            response.setCategory(club.getCategory());
            response.setImageUrl(club.getImageUrl());
            response.setCreatedAt(club.getCreateAt());
            response.setCreatedByUsername(club.getCreatedBy().getUsername());
            response.setMemberCount(clubMemberRepository.findByClub(club).size());

            return response;
        }).toList();
    }

    public List<ClubResponse> getMyClubs(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException("User not found"));

        return clubMemberRepository.findByUser(user).stream().map(member -> {
            Club club = member.getClub();
            ClubResponse response = new ClubResponse();
            response.setId(club.getId());
            response.setName(club.getName());
            response.setDescription(club.getDescription());
            response.setCategory(club.getCategory());
            response.setImageUrl(club.getImageUrl());
            response.setCreatedAt(club.getCreateAt());

            return response;
        }).toList();
    }
}
