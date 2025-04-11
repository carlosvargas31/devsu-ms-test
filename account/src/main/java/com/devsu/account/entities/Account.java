package com.devsu.account.entities;

import com.devsu.account.dtos.ClientDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_account", length = 10, nullable = false, unique = true)
    private String numberAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_account", nullable = false)
    private TypeAccount typeAccount;

    @Column(name = "initial_balance", precision = 10, scale = 2, nullable = false)
    private BigDecimal initialBalance;

    @Column(name = "status", nullable = false)
    private Boolean statusAccount;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Getter
    @RequiredArgsConstructor
    public enum TypeAccount {
        SAVINGS("Savings Account"),
        CHECKING("Checking Account");

        private final String name;

        @Override
        public String toString() {
            return this.name;
        }
    }

}
