package com.uiu.socialapp.socialapp.controller;

import com.uiu.socialapp.socialapp.dto.PostResponse;
import com.uiu.socialapp.socialapp.dto.UserResponse;
import com.uiu.socialapp.socialapp.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;

    SearchController(SearchService searchService){
        this.searchService=searchService;
    }

    @GetMapping("/users")
    public List<UserResponse> searchUser(@RequestParam String query) {
        return searchService.searchUsers(query);
    }

    @GetMapping("/posts")
    public List<PostResponse> searchPost(@RequestParam String query) {
        return searchService.searchPosts(query);
    }
}
