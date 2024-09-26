package co.com.bancolombia.jdbcclient.account;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRecordRepository implements AccountRepository {

    private final JdbcClient jdbcClient;

    @Override
    public Optional<Account> findById(int id) {
        String sql = """
        SELECT id, user_id, account, name, number, balance, currency, type, bank FROM account WHERE id = :id""";

        return jdbcClient.sql(sql)
                .param("id", id)
                .query(AccountRecord.class)
                .optional()
                .map(this::toEntity);
    }

    @Override
    public Account update(Account account) {
        var record = toRecord(account);
        String sql = """
        UPDATE ACCOUNT SET account=:account,balance=:balance,bank=:bank,currency=:currency,name=:name,number=:number
                 ,type=:type,user_id=:userId where id=:id""";
        int update = jdbcClient.sql(sql)
                .param("account", record.account())
                .param("balance", record.balance())
                .param("bank", record.bank())
                .param("currency", record.currency())
                .param("name", record.name())
                .param("number", record.number())
                .param("type", record.type())
                .param("userId", record.userId())
                .param("id", record.id())
                .update();
        if (update < 1)
            throw new IllegalStateException("Error updating account");

        return account;
    }

    private Account toEntity(AccountRecord record) {
        return new Account(record.id(), record.userId(), record.account(), record.name(),
                record.number(), record.balance(), record.currency(), record.type(), record.bank());
    }

    private AccountRecord toRecord(Account data) {
        return new AccountRecord(data.id(), data.userId(), data.account(), data.name(), data.number(), data.balance(),
                data.currency(), data.type(), data.bank());
    }
}
