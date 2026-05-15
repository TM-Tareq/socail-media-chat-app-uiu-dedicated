package com.uiu.socialapp.socialapp.controller;

import com.uiu.socialapp.socialapp.dto.EventRequest;
import com.uiu.socialapp.socialapp.dto.EventResponse;
import com.uiu.socialapp.socialapp.repository.EventRepository;
import com.uiu.socialapp.socialapp.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public EventResponse createEvent(@RequestBody EventRequest eventRequest, HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        return eventService.createEvent(eventRequest, email);
    }

    @PostMapping("/{eventId}/attend")
    public String JoinOrLeave(@PathVariable Long eventId, HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        return eventService.toggleJoinOrLeave(eventId, email);
    }

    @GetMapping
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/category")
    public List<EventResponse> getEventsByCategory(@RequestParam String category) {
        return eventService.getEventByCategory(category);
    }
}
