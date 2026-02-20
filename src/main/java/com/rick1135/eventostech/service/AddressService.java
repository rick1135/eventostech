package com.rick1135.eventostech.service;

import com.rick1135.eventostech.dto.EventRequestDTO;
import com.rick1135.eventostech.entity.Address;
import com.rick1135.eventostech.entity.Event;
import com.rick1135.eventostech.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public void createAddress(EventRequestDTO data, Event event) {
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.state());
        address.setEvent(event);
        this.addressRepository.save(address);
    }

    public Optional<Address> findByEventId(UUID eventId) {
        return this.addressRepository.findByEventId(eventId);
    }
}
