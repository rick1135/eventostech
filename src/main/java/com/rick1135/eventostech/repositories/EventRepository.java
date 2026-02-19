package com.rick1135.eventostech.repositories;

import com.rick1135.eventostech.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
