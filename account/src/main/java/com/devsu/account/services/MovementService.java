package com.devsu.account.services;

import com.devsu.account.dtos.MovementRequestDTO;
import com.devsu.account.dtos.MovementResponseDTO;
import com.devsu.account.model.AccountStatement;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface MovementService {

    Single<MovementResponseDTO> create(MovementRequestDTO movementRequest);
    Single<List<AccountStatement>> generateAccountStatement(Long clientId, String startDate, String endDate);

}
