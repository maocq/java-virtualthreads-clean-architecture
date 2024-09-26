package co.com.bancolombia.jpa.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class AccountData {

    @Id
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    private String account;
    private String name;
    private String number;
    private BigDecimal balance;
    private String currency;
    private String type;
    private String bank;
}
