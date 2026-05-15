package com.uiu.socialapp.socialapp.service;

import com.uiu.socialapp.socialapp.dto.EventRequest;
import com.uiu.socialapp.socialapp.dto.EventResponse;
import com.uiu.socialapp.socialapp.exception.CustomException;
import com.uiu.socialapp.socialapp.model.Event;
import com.uiu.socialapp.socialapp.model.EventAttendee;
import com.uiu.socialapp.socialapp.model.User;
import com.uiu.socialapp.socialapp.repository.EventAttendeeRepository;
import com.uiu.socialapp.socialapp.repository.EventRepository;
import com.uiu.socialapp.socialapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;
    private final UserRepository userRepository;

    EventService(EventRepository eventRepository, EventAttendeeRepository eventAttendeeRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.eventAttendeeRepository = eventAttendeeRepository;
        this.userRepository = userRepository;
    }

    public EventResponse createEvent(EventRequest request, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException("User not found"));

        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setCategory(request.getCategory());
        event.setDescription(request.getDescription());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());
        event.setLocation(request.getLocation());
        event.setCreatedBy(user);

        Event savedEvent = eventRepository.save(event);

        EventResponse response = new EventResponse();
        response.setId(savedEvent.getId());
        response.setTitle(savedEvent.getTitle());
        response.setDescription(savedEvent.getDescription());
        response.setCategory(savedEvent.getCategory());
        response.setCreatedByUsername(savedEvent.getCreatedBy().getUsername());
        response.setStartTime(savedEvent.getStartTime());
        response.setEndTime(savedEvent.getEndTime());
        response.setLocation(savedEvent.getLocation());
        response.setAttendeeCount(eventAttendeeRepository.findByEvent(event).size());

        return response;
    }

//    JoinOrLeaveEvnet()
    public String toggleJoinOrLeave(Long eventId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new CustomException("Event not found"));

        boolean alreadyJoined = eventAttendeeRepository.existsByUserAndEvent(user, event);

        if(alreadyJoined) {
            EventAttendee alreadyAttended = eventAttendeeRepository.findByUserAndEvent(user, event);
            eventAttendeeRepository.delete(alreadyAttended);
            return "Not interested";
        } else {
            EventAttendee newAttended = new EventAttendee();
            newAttended.setUser(user);
            newAttended.setEvent(event);
            eventAttendeeRepository.save(newAttended);
            return "Attending";
        }
    }

    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream().map(event -> {
            EventResponse response = new EventResponse();
            response.setId(event.getId());
            response.setTitle(event.getTitle());
            response.setDescription(event.getDescription());
            response.setCategory(event.getCategory());
            response.setCreatedByUsername(event.getCreatedBy().getUsername());
            response.setStartTime(event.getStartTime());
            response.setEndTime(event.getEndTime());
            response.setLocation(event.getLocation());
            response.setAttendeeCount(eventAttendeeRepository.findByEvent(event).size());

            return response;
        }).toList();
    }

    public List<EventResponse> getEventByCategory(String category) {
        return eventRepository.findByCategory(category).stream().map(event -> {
            EventResponse response = new EventResponse();
            response.setId(event.getId());
            response.setTitle(event.getTitle());
            response.setDescription(event.getDescription());
            response.setCategory(event.getCategory());
            response.setCreatedByUsername(event.getCreatedBy().getUsername());
            response.setStartTime(event.getStartTime());
            response.setEndTime(event.getEndTime());
            response.setLocation(event.getLocation());
            response.setAttendeeCount(eventAttendeeRepository.findByEvent(event).size());

            return response;
        }).toList();
    }
}