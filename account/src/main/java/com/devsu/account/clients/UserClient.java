package com.devsu.account.clients;

import com.devsu.account.dtos.ClientDTO;
import io.reactivex.rxjava3.core.Single;

public interface UserClient {

    Single<ClientDTO> findById(Long id);

}
