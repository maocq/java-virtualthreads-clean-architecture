package co.com.bancolombia.model.user.gateways;

import co.com.bancolombia.model.user.User;

public interface UserGateway {
    User get(int latency);
}
