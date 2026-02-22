package com.rick1135.eventostech.service;

import com.rick1135.eventostech.dto.CouponRequestDTO;
import com.rick1135.eventostech.dto.CouponResponseDTO;
import com.rick1135.eventostech.entity.Coupon;
import com.rick1135.eventostech.entity.Event;
import com.rick1135.eventostech.repositories.CouponRepository;
import com.rick1135.eventostech.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final EventRepository eventRepository;

    @Transactional
    public CouponResponseDTO addCouponToEvent(UUID eventId, CouponRequestDTO couponRequestDTO) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(()-> new IllegalArgumentException("Evento não encontrado"));

        Coupon coupon = new Coupon();
        coupon.setCode(couponRequestDTO.code());
        coupon.setDiscount(couponRequestDTO.discount());
        coupon.setValidUntil(couponRequestDTO.valid());
        coupon.setEvent(event);

        couponRepository.save(coupon);
        return  new CouponResponseDTO(
                coupon.getId(),
                coupon.getCode(),
                coupon.getDiscount(),
                coupon.getValidUntil()
        );
    }

    public List<Coupon> consultCoupons(UUID eventId, LocalDateTime currentDate) {
        return couponRepository.findByEventIdAndValidCoupons(eventId, currentDate);
    }
}
