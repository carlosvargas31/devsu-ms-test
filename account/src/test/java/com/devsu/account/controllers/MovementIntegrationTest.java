package com.devsu.account.controllers;

import com.devsu.account.clients.UserClient;
import com.devsu.account.dtos.ClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
class MovementIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserClient client;

    @Test
    void generateStatement_validInput_returnsOkAndStatementList() throws Exception {
        ClientDTO userClient = new ClientDTO("Carlos", 22, "+573117362461", "MALE", true, "Address test", "1234", "1234631231");
        Mockito.when(client.findById(1L)).thenReturn(Single.just(userClient));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/movements/statement")
                        .param("clientId", "1")
                        .param("startDate", "2025-04-10")
                        .param("endDate", "2025-04-15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertThat(result.getAsyncResult()).isNotNull();
    }

    @TestConfiguration
    static class UserClientTest {
        @Bean
        public UserClient client() {
            return Mockito.mock(UserClient.class);
        }
    }

}
