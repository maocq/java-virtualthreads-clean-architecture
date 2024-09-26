package co.com.bancolombia.jpa.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDataCrudRepository extends JpaRepository<AccountData, Integer> {
        //extends CrudRepository<AccountData, Integer>, QueryByExampleExecutor<AccountData> {
}
