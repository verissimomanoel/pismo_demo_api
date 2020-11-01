/*
 * Account.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Entity class to represent data of 'Account'.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@Document("Account")
@Getter
@Setter
public class Account {
    @ApiModelProperty(value = "Id of Item")
    @Id
    private String id;

    @NotNull
    @ApiModelProperty(value = "Number of document")
    @Indexed(name = "documentNumber")
    private String documentNumber;
}
