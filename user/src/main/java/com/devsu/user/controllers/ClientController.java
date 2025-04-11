package com.devsu.user.controllers;

import com.devsu.user.dtos.ClientDTO;
import com.devsu.user.entities.Client;
import com.devsu.user.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/clients")
public class ClientController {

    private final ClientService service;

    @GetMapping
    public List<Client> findAll() {
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Long id) {
        return this.service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Client create(@RequestBody ClientDTO client) {
        return this.service.create(client);
    }

    @PutMapping("/{id}")
    public Client update(@RequestBody ClientDTO client, @PathVariable Long id) {
        return this.service.update(client, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }

}
