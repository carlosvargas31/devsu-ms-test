package com.devsu.account.model;

import com.devsu.account.dtos.ClientDTO;
import com.devsu.account.entities.Account;
import com.devsu.account.entities.Movement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountStatement(
        LocalDateTime date,
        String client,
        String numberAccount,
        String typeAccount,
        Boolean statusAccount,
        BigDecimal initialBalance,
        BigDecimal movement,
        BigDecimal AvailableBalance
) {

    public AccountStatement(ClientDTO client, Account account, Movement movement) {
        this(
                movement.getDate(),
                client.name(),
                account.getNumberAccount(),
                account.getTypeAccount().getName(),
                account.getStatusAccount(),
                account.getInitialBalance(),
                movement.getMovementType().equals(Movement.MovementType.WITHDRAWAL) ? movement.getValue().negate() : movement.getValue(),
                movement.getBalance()
        );
    }

}
