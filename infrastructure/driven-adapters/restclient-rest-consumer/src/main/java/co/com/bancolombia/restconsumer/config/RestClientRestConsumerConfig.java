package co.com.bancolombia.restconsumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientRestConsumerConfig {

    private final String url;

    public RestClientRestConsumerConfig(@Value("${adapter.restconsumer.url}") String url) {
        this.url = url;
    }

    @Bean
    RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl(url)
                .requestFactory(new JdkClientHttpRequestFactory())
                .build();
    }
}
