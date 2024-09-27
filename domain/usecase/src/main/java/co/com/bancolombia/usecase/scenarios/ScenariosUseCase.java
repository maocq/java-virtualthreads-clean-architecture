package co.com.bancolombia.usecase.scenarios;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import co.com.bancolombia.model.balance.gateways.BalanceGateway;
import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserGateway;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class ScenariosUseCase {

    private final UserGateway userGateway;
    private final AccountRepository accountRepository;
    private final BalanceGateway balanceGateway;

    public User caseOne(int latency) {
        return userGateway.get(latency);
    }

    public Optional<Account> caseThree(int id) {
        return accountRepository.findById(id);
    }

    public Account caseFour(int id, int latency) {
        var account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account does not exist"));
        var user = userGateway.get(latency);
        var newAccount = account.toBuilder().userId(user.id()).build();
        return accountRepository.update(newAccount);
    }

    public String caseFive() {
        var id = "CC99" + ThreadLocalRandom.current().nextInt(1, 10000);
        balanceGateway.put(id);
        return id;
    }
}
