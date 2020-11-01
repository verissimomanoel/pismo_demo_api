/*
 * Transaction.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.transaction;

import br.com.pismo.demo.api.account.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity class to represent data of 'Transaction'.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@Document("Transaction")
@Getter
@Setter
public class Transaction {
    @Id
    private String id;

    @NotNull
    private Account account;

    @NotNull
    private OperationType operationType;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDateTime eventDate;
}
