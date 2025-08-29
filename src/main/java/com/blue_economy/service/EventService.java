package com.blue_economy.service;


import com.blue_economy.repository.EventRegisterMemberRepository;
import com.blue_economy.model.Event;
import com.blue_economy.model.EventRegisterMember;
import com.blue_economy.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private  final EventRegisterMemberRepository eventRegisterMemberRepository;

    public EventService(EventRepository eventRepository,
                        EventRegisterMemberRepository eventRegisterMemberRepository) {
        this.eventRepository = eventRepository;
        this.eventRegisterMemberRepository = eventRegisterMemberRepository;
    }


    ///  CREATE EVENT SERVICES-----------------------------------
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }


    public Event findEventById (Long id){
        return   eventRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Course not found"));
    }


    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        existing.setTitle(updatedEvent.getTitle());
        existing.setDescription(updatedEvent.getDescription());
        existing.setLocation(updatedEvent.getLocation());
        existing.setStartDate(updatedEvent.getStartDate());
        existing.setEndDate(updatedEvent.getEndDate());
        existing.setAllDay(updatedEvent.isAllDay());
        return eventRepository.save(existing);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }



/// REGISTER EVENT MEMBERS_____________________________________
    public EventRegisterMember registerEvent(EventRegisterMember event) {
        return eventRegisterMemberRepository.save(event);
    }

    public List<EventRegisterMember> getAllEventMembers() {
        return eventRegisterMemberRepository.findAll();
    }


    public EventRegisterMember getEventMemberById (Long id){
        return   eventRegisterMemberRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Course not found"));

    }

    public void deleteEventMember(Long id) {
        eventRegisterMemberRepository.deleteById(id);
    }







}
