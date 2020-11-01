/*
 * TransactionTO.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.transaction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

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

    @ApiModelProperty(value = "Amount of transaction")
    private BigDecimal amount;
}
