package com.rick1135.eventostech.controller;

import com.rick1135.eventostech.dto.EventRequestDTO;
import com.rick1135.eventostech.dto.EventResponseDTO;
import com.rick1135.eventostech.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDTO> create(@Valid @RequestBody EventRequestDTO body) {
        EventResponseDTO newEvent = this.eventService.createEvent(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEvent);
    }
}
