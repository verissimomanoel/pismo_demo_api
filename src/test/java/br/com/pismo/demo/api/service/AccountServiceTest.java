/*
 * AccountServiceTest.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.service;

import br.com.pismo.demo.api.account.Account;
import br.com.pismo.demo.api.account.AccountRepository;
import br.com.pismo.demo.api.account.AccountService;
import br.com.pismo.demo.api.account.impl.AccountServiceImpl;
import br.com.pismo.demo.api.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Implementation test for business class {@link AccountService}.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@SpringBootTest
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService = new AccountServiceImpl();

    /**
     * Init class Test.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenValidAccount_thenAccountShouldBeSaved() {
        Account account = getAccountMock();
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.save(account);
        assertNotNull(result);
    }

    @Test
    void whenDuplicatedAccountNumber_thenAccountShouldNotBeSaved() {
        Account account = getAccountMock();
        when(accountRepository.findAccountByDocumentNumber(account.getDocumentNumber()))
                .thenReturn(Optional.of(account));

        assertThrows(BusinessException.class, () -> {
            accountService.save(account);
        });
    }

    @Test
    void whenGetExistsAccount_thenAccountShouldBeFound() {
        String id = "123";
        Account account = getAccountMock();
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        Account result = accountService.getById(id);
        assertNotNull(result);
    }

    @Test
    void whenGetNotExistsAccount_thenAccountShouldNotBeFound() {
        String id = "123";
        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> {
            accountService.getById(id);
        });
    }

    @Test
    void whenGetWithoutAccountId_thenAccountShouldNotBeFound() {
        assertThrows(BusinessException.class, () -> {
            accountService.getById(null);
        });
    }

    /**
     * Return an instance of account.
     *
     * @return
     */
    private Account getAccountMock() {
        Account account = new Account();
        account.setId("123");
        account.setDocumentNumber("112233");

        return account;
    }
}
