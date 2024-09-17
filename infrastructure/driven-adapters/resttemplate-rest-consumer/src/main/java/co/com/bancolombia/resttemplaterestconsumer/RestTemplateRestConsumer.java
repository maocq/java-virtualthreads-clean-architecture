package co.com.bancolombia.resttemplaterestconsumer;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateRestConsumer implements UserGateway {

    private final RestTemplate restTemplate;
    private final String url;

    public RestTemplateRestConsumer(RestTemplate restTemplate, @Value("${adapter.restconsumer.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public User get(int latency) {
        var urlService = String.format("%s/%s", url, latency);
        return restTemplate.getForEntity(urlService, User.class)
                .getBody();
    }
}
