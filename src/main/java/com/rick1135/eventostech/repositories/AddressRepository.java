package com.rick1135.eventostech.repositories;

import com.rick1135.eventostech.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
