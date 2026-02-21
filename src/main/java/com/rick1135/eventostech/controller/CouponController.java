package com.rick1135.eventostech.controller;

import com.rick1135.eventostech.dto.CouponRequestDTO;
import com.rick1135.eventostech.dto.CouponResponseDTO;
import com.rick1135.eventostech.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/events/{eventId}/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<CouponResponseDTO> addCouponsToEvent(@PathVariable UUID eventId, @Valid @RequestBody CouponRequestDTO body) {
        CouponResponseDTO newCoupon = this.couponService.addCouponToEvent(eventId, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCoupon);
    }
}
