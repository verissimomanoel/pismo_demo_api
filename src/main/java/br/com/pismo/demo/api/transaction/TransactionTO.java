/*
 * TransactionTO.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.transaction;

import br.com.pismo.demo.api.account.Account;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 'TransactionTO'.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@Getter
@Setter
public class TransactionTO implements Serializable {
    private static final long serialVersionUID = 1536609573526248057L;

    @ApiModelProperty(value = "Id of Item")
    private String id;

    @NotNull
    @ApiModelProperty(value = "Account id of transaction")
    private String accountId;

    @NotNull
    @ApiModelProperty(value = "Operation type of transactions")
    private Integer operationType;

    @NotNull
    @ApiModelProperty(value = "Amount of transaction")
    private Double amount;

    @NotNull
    @ApiModelProperty(value = "Date and time of transaction")
    private LocalDateTime eventDate;
}
