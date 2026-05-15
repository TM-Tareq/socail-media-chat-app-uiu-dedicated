package com.uiu.socialapp.socialapp.repository;

import com.uiu.socialapp.socialapp.model.Event;
import com.uiu.socialapp.socialapp.model.EventAttendee;
import com.uiu.socialapp.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventAttendeeRepository extends JpaRepository<EventAttendee,Long> {
    boolean existsByUserAndEvent(User user, Event event);
    List<EventAttendee>findByEvent(Event event);
    EventAttendee findByUserAndEvent(User user, Event event);
}
