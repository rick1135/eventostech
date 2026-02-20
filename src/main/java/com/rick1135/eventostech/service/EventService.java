package com.rick1135.eventostech.service;

import com.rick1135.eventostech.dto.EventRequestDTO;
import com.rick1135.eventostech.dto.EventResponseDTO;
import com.rick1135.eventostech.entity.Event;
import com.rick1135.eventostech.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
