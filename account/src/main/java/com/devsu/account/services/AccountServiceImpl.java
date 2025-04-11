package com.devsu.account.services;

import com.devsu.account.clients.UserClient;
import com.devsu.account.dtos.AccountRequestDTO;
import com.devsu.account.dtos.AccountResponseDTO;
import com.devsu.account.entities.Account;
import com.devsu.account.model.UserAccounts;
import com.devsu.account.repositories.AccountRepository;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final Random random = new Random();

    private final UserClient client;
    private final AccountRepository repository;

    @Override
    public Single<List<AccountResponseDTO>> findAll() {
        return Single.fromCallable(this.repository::findAll)
                .subscribeOn(Schedulers.io())
                .flatMapObservable(Observable::fromIterable)
                .flatMapSingle(account ->
                        this.client.findById(account.getClientId())
                                .subscribeOn(Schedulers.io())
                                .map(userClient -> new AccountResponseDTO(userClient, account)))
                .toList();
    }

    @Override
    public Single<AccountResponseDTO> deactivate(String numberAccount) {
        return Single.fromCallable(() -> this.repository.findByNumberAccount(numberAccount))
                .subscribeOn(Schedulers.io())
                .flatMap(accountOpt ->
                        accountOpt.map(account ->
                                this.client.findById(account.getClientId())
                                        .subscribeOn(Schedulers.io())
                                        .flatMap(userClient -> {
                                            account.setStatusAccount(Boolean.FALSE);
                                            return Single.fromCallable(() -> this.repository.save(account))
                                                    .subscribeOn(Schedulers.io())
                                                    .map(updatedAccount -> new AccountResponseDTO(userClient, updatedAccount));
                                        })
                                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account doesn't exists"))
                );
    }

    @Override
    public Single<AccountResponseDTO> create(AccountRequestDTO accountRequest) {
        return this.client.findById(accountRequest.clientId())
                .subscribeOn(Schedulers.io())
                .flatMap(userClient ->
                        Single.fromCallable(() -> this.repository.existsByClientIdAndTypeAccountAndStatusAccountIsTrue(accountRequest.clientId(), accountRequest.typeAccount()))
                                .subscribeOn(Schedulers.io())
                                .flatMap(exists -> {
                                    if (exists) return Single.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client's already had an account"));
                                    Account account = new Account();
                                    account.setStatusAccount(Boolean.TRUE);
                                    account.setClientId(accountRequest.clientId());
                                    account.setTypeAccount(accountRequest.typeAccount());
                                    account.setNumberAccount(this.generateNumberAccount());
                                    account.setInitialBalance(accountRequest.initialBalance());

                                    return Single.fromCallable(() -> this.repository.save(account))
                                            .subscribeOn(Schedulers.io())
                                            .map(saveAccount -> new AccountResponseDTO(userClient, saveAccount));
                                })
                );
    }

    @Override
    public Single<Account> findById(Long accountId) {
        return Single.fromCallable(() -> this.repository.findById(accountId))
                .subscribeOn(Schedulers.io())
                .map(account ->
                        account.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account doesn't exists"))
                );
    }

    @Override
    public Single<UserAccounts> findByClient(Long clientId) {
        return this.client.findById(clientId)
                .subscribeOn(Schedulers.io())
                .flatMap(userClient ->
                        Single.fromCallable(() -> this.repository.findByClientId(clientId))
                                .subscribeOn(Schedulers.io())
                                .map(accounts -> new UserAccounts(userClient, accounts))
                );
    }

    private String generateNumberAccount() {
        StringBuilder numberAccount = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            numberAccount.append(this.random.nextInt(10));
        }

        return numberAccount.toString();
    }

}
