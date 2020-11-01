/*
 * Transaction.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.transaction;

import br.com.pismo.demo.api.account.Account;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
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
    @ApiModelProperty(value = "Id of Item")
    @Id
    private String id;

    @NotNull
    @ApiModelProperty(value = "Account owner of transaction")
    private Account account;

    @NotNull
    @ApiModelProperty(value = "Operation type of transactions")
    private OperationType operationType;

    @NotNull
    @ApiModelProperty(value = "Amount of transaction")
    private Double amount;

    @NotNull
    @ApiModelProperty(value = "Date and time of transaction")
    private LocalDateTime eventDate;
}
