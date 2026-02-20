package com.rick1135.eventostech.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.*;

public record EventRequestDTO(
        @NotBlank(message = "O título deve ser informado") String title,
        String description,
        @NotNull(message = "A data deve ser informada") LocalDateTime date,
        String city,
        String state,
        @NotNull(message = "O tipo do evento (remoto/presencial) deve ser informado") Boolean remote,
        @Pattern(regexp = "^(https?://)(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,63}){1,3}(/[a-zA-Z0-9._~:/?#\\[\\]@!$&'()*+,;=-]*)?$", message = "URL inválida")
        String eventUrl,
        String imgUrl) {
}
