package com.rick1135.eventostech.exception;

import java.util.List;

public record ErrorResponseDTO(
        int status,
        String message,
        List<String> details
) {
}
