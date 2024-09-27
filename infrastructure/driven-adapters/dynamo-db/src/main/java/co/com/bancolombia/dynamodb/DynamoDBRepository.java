package co.com.bancolombia.dynamodb;

import co.com.bancolombia.model.balance.gateways.BalanceGateway;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class DynamoDBRepository implements BalanceGateway {

    private final DynamoDbTable<BalanceEntity> balanceTable;

    public DynamoDBRepository(DynamoDbEnhancedClient connectionFactory) {
        balanceTable = connectionFactory.table("saldos_test", TableSchema.fromBean(BalanceEntity.class));
    }

    @Override
    public void put(String id) {
        BalanceEntity balanceEntity = new BalanceEntity(id, "S|T|000000000624537", "Name", "Other");
        balanceTable.putItem(balanceEntity);
    }
}
