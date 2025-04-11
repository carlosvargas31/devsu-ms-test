package com.devsu.account.dtos;

public record ClientDTO(
        String name,
        Integer age,
        String phone,
        String gender,
        Boolean status,
        String address,
        String password,
        String identification
) { }
