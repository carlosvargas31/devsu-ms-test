package com.devsu.account.repositories;

import com.devsu.account.entities.Account;
import com.devsu.account.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    Optional<Movement> findTopByAccountOrderByDateDesc(Account account);
    List<Movement> findByAccountAndDateBetweenOrderByDateDesc(Account account, LocalDateTime dateAfter, LocalDateTime dateBefore);

}
