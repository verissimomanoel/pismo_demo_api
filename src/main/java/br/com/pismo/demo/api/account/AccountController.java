/*
 * AccountController.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.account;

import br.com.pismo.demo.api.exception.MessageResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Control class referring to manage accounts.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@RestController
@Api(tags = "Account API")
@RequestMapping("${app.api.base}/accounts")
@Scope("prototype")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * Create a new account.
     *
     * @param account
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Create a new account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "Success", response = Account.class),
            @ApiResponse(code = 403, message = "Denied", response = MessageResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> create(
            @ApiParam(value = "Account informations", required = true) @Valid @RequestBody final Account account) {
        Account accountSaved = accountService.save(account);
        return ResponseEntity.ok(accountSaved);
    }

    /**
     * Search account using id reported.
     *
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Search account using id reported", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "Success", response = Account.class),
            @ApiResponse(code = 403, message = "Denied", response = MessageResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class)
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> searchById(
            @ApiParam(value = "Id of account") @PathVariable final String id) {
        Account account = accountService.getById(id);

        return ResponseEntity.ok(account);
    }
}
