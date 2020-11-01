/*
 * TransactionController.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.transaction;

import br.com.pismo.demo.api.exception.MessageResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "Transaction API")
@RequestMapping("${app.api.base}/transactions")
@Scope("prototype")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    /**
     * Create a new transaction.
     *
     * @param transactionTO
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Create a new transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "Success", response = Transaction.class),
            @ApiResponse(code = 403, message = "Denied", response = MessageResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionTO> create(
            @ApiParam(value = "Transaction informations", required = true) @Valid @RequestBody final TransactionTO transactionTO) {
        Transaction transaction = transactionService.getTransaction(transactionTO);
        transactionService.save(transaction);
        transactionTO.setId(transaction.getId());
        return ResponseEntity.ok(transactionTO);
    }
}
