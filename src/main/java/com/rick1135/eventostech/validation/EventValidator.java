package com.rick1135.eventostech.validation;

import com.rick1135.eventostech.dto.EventRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static org.apache.logging.log4j.util.Strings.isBlank;

public class EventValidator implements ConstraintValidator<EventValidation, EventRequestDTO> {
    @Override
    public boolean isValid(EventRequestDTO eventRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
        if(eventRequestDTO==null || eventRequestDTO.remote()==null)
            return false;

        if(eventRequestDTO.remote()){
            return !isBlank(eventRequestDTO.eventUrl());
        } else
            return !isBlank(eventRequestDTO.city()) && !isBlank(eventRequestDTO.state());
    }
}
