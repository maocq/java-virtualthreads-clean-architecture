package co.com.bancolombia.restconsumer;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class RestClientRestConsumer implements UserGateway {
    private final RestClient restClient;

    @Override
    public User get(int latency) {
        return restClient
                .get()
                .uri("/{latency}", latency)
                .retrieve()
                .body(User.class);
    }
}
