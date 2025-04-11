package com.devsu.account.dtos;

import com.devsu.account.entities.Movement;

import java.math.BigDecimal;

public record MovementRequestDTO(
        Long accountId,
        Movement.MovementType movementType,
        BigDecimal value
) { }
