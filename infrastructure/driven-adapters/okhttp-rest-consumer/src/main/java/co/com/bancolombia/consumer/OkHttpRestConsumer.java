package co.com.bancolombia.consumer;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OkHttpRestConsumer implements UserGateway {

    private final String url;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public OkHttpRestConsumer(@Value("${adapter.restconsumer.url}") String url, OkHttpClient client, ObjectMapper mapper) {
        this.url = url;
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public User get(int latency) {
        var urlService = String.format("%s/%s", url, latency);
        Request request = new Request.Builder()
                .url(urlService)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();

        return callAndMap(request);
    }

    @SneakyThrows
    private User callAndMap(Request request) {
        var response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), User.class);
    }
}
