package com.rick1135.eventostech.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record CouponRequestDTO(
        @NotBlank String code,
        @Positive @Max(100) Integer discount,
        @Future LocalDateTime valid) {
}
