package com.rick1135.eventostech.controller;

import com.rick1135.eventostech.dto.EventDetailsDTO;
import com.rick1135.eventostech.dto.EventRequestDTO;
import com.rick1135.eventostech.dto.EventResponseDTO;
import com.rick1135.eventostech.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<Page<EventResponseDTO>> getEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String uf,
            @RequestParam(required = false) Boolean remote,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
            ) {
        Page<EventResponseDTO> allEvents = this.eventService.getUpcomingEvents(page, size, title, city, uf, remote, startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(allEvents);

    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDetailsDTO> getEventDetails(@PathVariable UUID eventId) {
        EventDetailsDTO eventDetails = this.eventService.getEventDetails(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(eventDetails);
    }
}
