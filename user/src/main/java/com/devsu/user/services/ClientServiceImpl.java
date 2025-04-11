package com.devsu.user.services;

import com.devsu.user.dtos.ClientDTO;
import com.devsu.user.entities.Client;
import com.devsu.user.entities.Person;
import com.devsu.user.exceptions.ClientAlreadyExistsException;
import com.devsu.user.exceptions.ClientNotFoundException;
import com.devsu.user.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) throw new ClientNotFoundException("Client not found");
        clientRepository.deleteById(id);
    }

    @Override
    public Client getById(Long id) {
        return this.clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }

    @Override
    public Client create(ClientDTO client) {
        Optional<Client> existingClient = clientRepository.findByIdentification(client.identification());
        if (existingClient.isPresent()) throw new ClientAlreadyExistsException("Client already exists");

        Client newClient = new Client();
        newClient.setAge(client.age());
        newClient.setName(client.name());
        newClient.setPhone(client.phone());
        newClient.setStatus(client.status());
        newClient.setAddress(client.address());
        newClient.setPassword(client.password());
        newClient.setIdentification(client.identification());
        newClient.setGender(Person.Gender.valueOf(client.gender()));

        return this.clientRepository.save(newClient);
    }

    @Override
    public Client update(ClientDTO client, Long id) {
        Client existingClient = this.clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));

        if (client.age() != null) existingClient.setAge(client.age());
        if (client.name() != null) existingClient.setName(client.name());
        if (client.phone() != null) existingClient.setPhone(client.phone());
        if (client.status() != null) existingClient.setStatus(client.status());
        if (client.address() != null) existingClient.setAddress(client.address());
        if (client.password() != null) existingClient.setPassword(client.password());
        if (client.identification() != null) existingClient.setIdentification(client.identification());

        return this.clientRepository.save(existingClient);
    }

    @Override
    public List<Client> getAll() {
        return this.clientRepository.findByStatusIsTrue();
    }

}
