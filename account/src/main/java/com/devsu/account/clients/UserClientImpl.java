package com.devsu.account.clients;

import com.devsu.account.dtos.ClientDTO;
import com.devsu.account.exceptions.ClientNotFoundException;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserClientImpl implements UserClient {

    @Value("${ms.clients.url}")
    private String baseUrl;
    private final RestTemplate restTemplate;

    @Override
    public Single<ClientDTO> findById(Long id) {
        return Single.fromCallable(() ->  {
            try {
                String url = "%s/%s".formatted(this.baseUrl, id);

                ResponseEntity<ClientDTO> response = this.restTemplate.getForEntity(url, ClientDTO.class);

                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    return response.getBody();
                } else {
                    throw new ClientNotFoundException("Client not found");
                }
            } catch (RestClientException e) {
                throw new ClientNotFoundException("Client not found");
            }
        }).subscribeOn(Schedulers.io());
    }

}
