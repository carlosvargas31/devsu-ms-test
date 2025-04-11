package com.devsu.account.controllers;

import com.devsu.account.dtos.AccountRequestDTO;
import com.devsu.account.dtos.AccountResponseDTO;
import com.devsu.account.services.AccountService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/accounts")
public class AccountController {

    private final AccountService service;

    @GetMapping
    public Single<List<AccountResponseDTO>> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Single<AccountResponseDTO> create(@RequestBody AccountRequestDTO accountRequest) {
        return this.service.create(accountRequest);
    }

    @PatchMapping("/{numberAccount}")
    public Single<AccountResponseDTO> deactivate(@PathVariable String numberAccount) {
        return this.service.deactivate(numberAccount);
    }

}
