package com.rick1135.eventostech.dto;

import java.time.LocalDateTime;
import java.util.*;

public record EventDetailsDTO(
        UUID id,
        String title,
        String description,
        LocalDateTime date,
        String city,
        String state,
        String imgUrl,
        String eventUrl,
        List<CouponDTO> coupons) {

    public record CouponDTO(
            String code,
            Integer discount,
            LocalDateTime valid) {
    }
}