/*
 * TransactionServiceTest.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.service;

import br.com.pismo.demo.api.account.Account;
import br.com.pismo.demo.api.account.AccountRepository;
import br.com.pismo.demo.api.account.AccountService;
import br.com.pismo.demo.api.exception.BusinessException;
import br.com.pismo.demo.api.transaction.*;
import br.com.pismo.demo.api.transaction.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
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
class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private TransactionService transactionService = new TransactionServiceImpl();

    /**
     * Init class Test.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenValidTransaction_thenTransactionShouldBeSaved() {
        TransactionTO transactionTO = getTransactionTOMock(OperationType.PAGAMENTO);
        Account account = getAccountMock();
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        when(accountService.getById(account.getId())).thenReturn(account);
        Transaction transaction = transactionService.getTransaction(transactionTO);
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction result = transactionService.save(transaction);
        assertNotNull(result);
    }

    @Test
    void whenValidTransactionSaque_thenTransactionShouldBeSaved() {
        TransactionTO transactionTO = getTransactionTOMock(OperationType.SAQUE);
        Account account = getAccountMock();
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        when(accountService.getById(account.getId())).thenReturn(account);
        Transaction transaction = transactionService.getTransaction(transactionTO);
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction result = transactionService.save(transaction);
        assertNotNull(result);
    }

    @Test
    void whenZeroAmount_thenTransactionShouldNotBeSaved() {
        TransactionTO transactionTO = getTransactionTOMock(OperationType.SAQUE);
        transactionTO.setAmount(transactionTO.getAmount().negate());
        Account account = getAccountMock();
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        when(accountService.getById(account.getId())).thenReturn(account);

        assertThrows(BusinessException.class, () -> {
            transactionService.getTransaction(transactionTO);
        });
    }

    /**
     * Return an instance of transactionTO.
     *
     * @return
     */
    private TransactionTO getTransactionTOMock(OperationType operationType) {
        TransactionTO transactionTO = new TransactionTO();
        transactionTO.setOperationType(operationType.getId());
        transactionTO.setAmount(BigDecimal.valueOf(1000));
        transactionTO.setAccountId(getAccountMock().getId());

        return transactionTO;
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
