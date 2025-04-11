package com.devsu.account.services;

import com.devsu.account.dtos.MovementRequestDTO;
import com.devsu.account.dtos.MovementResponseDTO;
import com.devsu.account.entities.Account;
import com.devsu.account.entities.Movement;
import com.devsu.account.model.AccountStatement;
import com.devsu.account.repositories.MovementRepository;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final AccountService accountService;
    private final MovementRepository repository;

    @Override
    public Single<MovementResponseDTO> create(MovementRequestDTO movementRequest) {
        return this.accountService.findById(movementRequest.accountId())
                .subscribeOn(Schedulers.io())
                .flatMap(account ->
                        Single.fromCallable(() -> this.repository.findTopByAccountOrderByDateDesc(account))
                                .subscribeOn(Schedulers.io())
                                .map(movement -> {
                                    BigDecimal currentBalance = movement.map(Movement::getBalance).orElse(account.getInitialBalance());
                                    return this.processMovement(movementRequest, account, currentBalance);
                                }))
                .flatMap(movement -> Single.fromCallable(() -> this.repository.save(movement)))
                .subscribeOn(Schedulers.io())
                .map(MovementResponseDTO::new);
    }

    @Override
    public Single<List<AccountStatement>> generateAccountStatement(Long clientId, String startDate, String endDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime endDateTime = LocalDate.parse(endDate, formatter).atStartOfDay();
            LocalDateTime startDateTime = LocalDate.parse(startDate, formatter).atStartOfDay();

            return this.accountService.findByClient(clientId)
                    .subscribeOn(Schedulers.io())
                    .flatMap(userAccounts ->
                            Single.fromCallable(() -> userAccounts.accounts().stream()
                                    .flatMap(account -> {
                                        List<Movement> movements = this.repository.findByAccountAndDateBetweenOrderByDateDesc(account, startDateTime, endDateTime);
                                        return movements.stream().map(movement -> new AccountStatement(userAccounts.client(), account, movement));
                                    })
                                    .toList()
                            )
                    );
        } catch (DateTimeParseException e) {
            return Single.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Use yyyy-MM-dd"));
        }
    }

    private Movement processMovement(MovementRequestDTO movementRequest, Account account, BigDecimal currentBalance) {
        Movement movement = new Movement();
        movement.setAccount(account);
        movement.setDate(LocalDateTime.now());
        movement.setValue(movementRequest.value());
        movement.setMovementType(movementRequest.movementType());

        BigDecimal newBalance = switch (movementRequest.movementType()) {
            case DEPOSIT -> currentBalance.add(movementRequest.value());
            case WITHDRAWAL -> {
                if (currentBalance.compareTo(movementRequest.value()) < 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
                yield currentBalance.subtract(movementRequest.value());
            }
        };

        movement.setBalance(newBalance);
        return movement;
    }

}
