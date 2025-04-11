package com.devsu.account.services;

import com.devsu.account.dtos.AccountRequestDTO;
import com.devsu.account.dtos.AccountResponseDTO;
import com.devsu.account.entities.Account;
import com.devsu.account.model.UserAccounts;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface AccountService {

    Single<List<AccountResponseDTO>> findAll();
    Single<AccountResponseDTO> deactivate(String numberAccount);
    Single<AccountResponseDTO> create(AccountRequestDTO accountRequest);

    Single<Account> findById(Long accountId);
    Single<UserAccounts> findByClient(Long clientId);


}
