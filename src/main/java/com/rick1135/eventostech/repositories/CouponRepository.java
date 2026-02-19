package com.rick1135.eventostech.repositories;

import com.rick1135.eventostech.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {
    @Query("SELECT c FROM Coupon c WHERE c.event.id = :eventId AND (c.validUntil IS NULL OR c.validUntil > :currentDate)")
    List<Coupon> findByEventIdAndValidCoupons(@Param("eventId") UUID eventId, @Param("currentDate") LocalDateTime currentDate);
}