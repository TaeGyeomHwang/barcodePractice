package com.boot.barcodepractice.controller;

import com.boot.barcodepractice.entity.CalendarEvent;
import com.boot.barcodepractice.service.CalendarEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class CalendarEventController {

    @Autowired
    private CalendarEventService service;

    @GetMapping
    public List<CalendarEvent> getAllEvents() {
        return service.findAllEvents();
    }

    @GetMapping("/{id}")
    public CalendarEvent getEventById(@PathVariable Long id) {
        return service.findEventById(id).orElse(null);
    }

    @PostMapping
    public CalendarEvent createEvent(@RequestBody CalendarEvent event) {
        return service.saveEvent(event);
    }

    @PutMapping("/{id}")
    public CalendarEvent updateEvent(@PathVariable Long id, @RequestBody CalendarEvent event) {
        event.setEventNo(id);
        return service.saveEvent(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        service.deleteEvent(id);
    }
}
