package co.com.bancolombia.jpa.account;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountDataRepository implements AccountRepository {

    private final AccountDataCrudRepository crudRepository;

    @Override
    public Optional<Account> findById(int id) {
        return crudRepository.findById(id)
                .map(this::toEntity);
    }

    @Override
    public Account update(Account account) {
        var accountData = crudRepository.save(toData(account));
        return toEntity(accountData);
    }

    private Account toEntity(AccountData data) {
        return new Account(data.getId(), data.getUserId(), data.getAccount(), data.getName(),
                data.getNumber(), data.getBalance(), data.getCurrency(), data.getType(), data.getBank());
    }

    private AccountData toData(Account data) {
        return new AccountData(data.id(), data.userId(), data.account(), data.name(), data.number(), data.balance(),
                data.currency(), data.type(), data.bank());
    }
}
