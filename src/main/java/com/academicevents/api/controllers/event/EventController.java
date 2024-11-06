package com.academicevents.api.controllers.event;

import com.academicevents.api.DTO.event.DeleteEventDTO;
import com.academicevents.api.DTO.event.EventDTO;
import com.academicevents.api.DTO.event.SearchEventDTO;
import com.academicevents.api.DTO.event.SubscribeEventDTO;
import com.academicevents.api.handlers.EventHandlers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EventController {
    @PostMapping("/create/event")
    public ResponseEntity<?> createEvent(@RequestBody EventDTO event) {
        return EventHandlers.createEvent(event);
    }

    @PostMapping("get/event")
    public EventDTO getEventbyName(@RequestBody SearchEventDTO event){
        return EventHandlers.getEventbyName(event);
    }

    @GetMapping("/get/listevents")
    public ResponseEntity<?> listEvents() {
        return EventHandlers.listEvents();
    }

    @PostMapping("subscribe/event")
    public ResponseEntity<?> subscribeEvent(@RequestBody SubscribeEventDTO event) {
        Map<String, String> response = new HashMap<>();
        if (EventHandlers.subscribeEvent(event)) {
            response.put("message", "Inscricão realizada com sucesso!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/event")
    public ResponseEntity<?> deleteEvent(@RequestBody DeleteEventDTO event) {
        return EventHandlers.deleteEvent(event);
    }
}
