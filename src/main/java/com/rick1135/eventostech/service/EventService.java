package com.rick1135.eventostech.service;

import com.rick1135.eventostech.dto.EventRequestDTO;
import com.rick1135.eventostech.dto.EventResponseDTO;
import com.rick1135.eventostech.entity.Event;
import com.rick1135.eventostech.repositories.EventRepository;
import com.rick1135.eventostech.repositories.EventSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AddressService addressService;

    @Transactional
    public EventResponseDTO createEvent(EventRequestDTO data) {
        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(data.date());
        newEvent.setRemote(data.remote());

        if(data.imgUrl() != null) {
            newEvent.setImgUrl(data.imgUrl());
        } else {
            newEvent.setImgUrl("https://www.pngall.com/wp-content/uploads/5/Events-PNG-High-Quality-Image.png");
        }

        this.eventRepository.save(newEvent);

        if (!data.remote()) {
            this.addressService.createAddress(data, newEvent);
        }

        return new EventResponseDTO(
                newEvent.getId(),
                newEvent.getTitle(),
                newEvent.getDescription(),
                newEvent.getDate(),
                data.city(),
                data.state(),
                newEvent.getRemote(),
                newEvent.getEventUrl(),
                newEvent.getImgUrl()
        );
    }

    public Page<EventResponseDTO> getUpcomingEvents(int page, int size, String title, String city, String uf, Boolean remote, LocalDateTime startDate, LocalDateTime endDate) {
        Pageable pageable = PageRequest.of(page, size);

        if(startDate == null) {
            startDate = LocalDateTime.now();
        }

        Specification<Event> spec = Specification.where(EventSpecification.hasTitle(title))
                .and(EventSpecification.hasCity(city))
                .and(EventSpecification.hasUf(uf))
                .and(EventSpecification.isRemote(remote))
                .and(EventSpecification.startDateAfter(startDate))
                .and(EventSpecification.endDateBefore(endDate));

        return this.eventRepository.findAll(spec, pageable).map(event -> new EventResponseDTO (
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getAddress() != null ? event.getAddress().getCity() : null,
                event.getAddress() != null ? event.getAddress().getUf() : null,
                event.getRemote(),
                event.getEventUrl(),
                event.getImgUrl()
        ));
    }
}
