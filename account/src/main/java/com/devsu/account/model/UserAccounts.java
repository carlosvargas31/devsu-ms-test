package com.devsu.account.model;

import com.devsu.account.dtos.ClientDTO;
import com.devsu.account.entities.Account;

import java.util.List;

public record UserAccounts(
        ClientDTO client,
        List<Account> accounts
) { }
