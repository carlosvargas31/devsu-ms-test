package com.devsu.account.repositories;

import com.devsu.account.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByNumberAccount(String numberAccount);

    List<Account> findByClientId(Long clientId);
    Boolean existsByClientIdAndTypeAccountAndStatusAccountIsTrue(Long clientId, Account.TypeAccount typeAccount);

}
