package com.devsu.account.dtos;

import com.devsu.account.entities.Movement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovementResponseDTO(
        String numberAccount,
        String typeAccount,
        BigDecimal initialBalance,
        Boolean statusAccount,
        LocalDateTime date,
        String movementType,
        BigDecimal value,
        BigDecimal balance
) {

    public MovementResponseDTO(Movement movement) {
        this(
                movement.getAccount().getNumberAccount(),
                movement.getAccount().getTypeAccount().getName(),
                movement.getAccount().getInitialBalance(),
                movement.getAccount().getStatusAccount(),
                movement.getDate(),
                movement.getMovementType().getValue(),
                movement.getValue(),
                movement.getBalance()
        );
    }

}
