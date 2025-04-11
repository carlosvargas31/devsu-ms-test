package com.devsu.user.repositories;

import com.devsu.user.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByStatusIsTrue();
    Optional<Client> findByIdentification(String identification);

}
