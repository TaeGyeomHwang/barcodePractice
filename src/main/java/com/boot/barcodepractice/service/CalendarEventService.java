package com.boot.barcodepractice.service;

import com.boot.barcodepractice.entity.CalendarEvent;
import com.boot.barcodepractice.repository.CalendarEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarEventService {

    @Autowired
    private CalendarEventRepository repository;

    public List<CalendarEvent> findAllEvents() {
        return repository.findAll();
    }

    public Optional<CalendarEvent> findEventById(Long eventNo) {
        return repository.findById(eventNo);
    }

    public CalendarEvent saveEvent(CalendarEvent event) {
        return repository.save(event);
    }

    public void deleteEvent(Long eventNo) {
        repository.deleteById(eventNo);
    }
}
