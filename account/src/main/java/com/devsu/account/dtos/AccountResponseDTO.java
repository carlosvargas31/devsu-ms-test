package com.devsu.account.dtos;

import com.devsu.account.entities.Account;

import java.math.BigDecimal;

public record AccountResponseDTO(
        String numberAccount,
        String typeAccount,
        BigDecimal initialBalance,
        Boolean statusAccount,
        String client
) {
    public AccountResponseDTO(ClientDTO client, Account account) {
        this(account.getNumberAccount(), account.getTypeAccount().getName(), account.getInitialBalance(), account.getStatusAccount(), client.name());
    }
}
