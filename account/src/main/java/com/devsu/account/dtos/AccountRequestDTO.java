package com.devsu.account.dtos;

import com.devsu.account.entities.Account;

import java.math.BigDecimal;

public record AccountRequestDTO(
        String numberAccount,
        Account.TypeAccount typeAccount,
        BigDecimal initialBalance,
        Long clientId
) { }
