package com.rick1135.eventostech.dto;

import com.rick1135.eventostech.validation.EventValidation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.*;

@EventValidation
public record EventRequestDTO(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull LocalDateTime date,
        String city,
        String state,
        @NotNull Boolean remote,
        String eventUrl,
        String imgUrl) {
}
