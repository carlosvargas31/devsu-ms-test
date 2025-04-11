package com.devsu.user.services;

import com.devsu.user.dtos.ClientDTO;
import com.devsu.user.entities.Client;

import java.util.List;

public interface ClientService {

    void delete(Long id);

    Client getById(Long id);
    Client create(ClientDTO client);
    Client update(ClientDTO client, Long id);

    List<Client> getAll();

}
