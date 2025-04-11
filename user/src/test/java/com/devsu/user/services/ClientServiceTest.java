package com.devsu.user.services;

import com.devsu.user.exceptions.ClientNotFoundException;
import com.devsu.user.repositories.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_delete() {
        Mockito.when(repository.existsById(1L)).thenReturn(true);

        service.delete(1L);

        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void test_deleteThrowError() {
        Mockito.when(repository.existsById(1L)).thenReturn(false);

        Assertions.assertThrows(ClientNotFoundException.class, () -> service.delete(1L));

        Mockito.verify(repository, Mockito.never()).deleteById(1L);
    }

}
