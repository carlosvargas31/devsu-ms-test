package com.devsu.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "clients")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id_client")
public class Client extends Person {

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Boolean status;

}
