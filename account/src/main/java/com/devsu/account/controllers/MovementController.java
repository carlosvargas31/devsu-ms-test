package com.devsu.account.controllers;

import com.devsu.account.dtos.MovementRequestDTO;
import com.devsu.account.dtos.MovementResponseDTO;
import com.devsu.account.model.AccountStatement;
import com.devsu.account.services.MovementService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/movements")
public class MovementController {

    private final MovementService service;

    @PostMapping
    public Single<MovementResponseDTO> create(@RequestBody MovementRequestDTO movementRequest) {
        return this.service.create(movementRequest);
    }

    @GetMapping("statement")
    public Single<List<AccountStatement>> generateStatement(@RequestParam Long clientId, @RequestParam String startDate, @RequestParam String endDate) {
        return this.service.generateAccountStatement(clientId, startDate, endDate);
    }

}
