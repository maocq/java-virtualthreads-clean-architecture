package co.com.bancolombia.jdbcclient.account;

import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record AccountRecord(
        Integer id, String userId, String account, String name, String number, BigDecimal balance, String currency,
        String type, String bank) {
}
