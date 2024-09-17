package co.com.bancolombia.usecase.scenarios;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScenariosUseCase {

    private final UserGateway userGateway;

    public User caseOne(int latency) {
        return userGateway.get(latency);
    }
}
