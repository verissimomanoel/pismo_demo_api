/*
 * TransactionServiceImpl.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.transaction.impl;

import br.com.pismo.demo.api.account.Account;
import br.com.pismo.demo.api.account.AccountService;
import br.com.pismo.demo.api.exception.BusinessException;
import br.com.pismo.demo.api.exception.PismoDemoMessageCode;
import br.com.pismo.demo.api.transaction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Business class of {@link Transaction}.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@Service
@Scope("prototype")
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    /**
     * @see TransactionService#save(Transaction)
     */
    @Override
    public Transaction save(Transaction transaction) {
        transaction = transactionRepository.save(transaction);

        return transaction;
    }

    /**
     * @see TransactionService#getTransaction(TransactionTO)
     */
    @Override
    public Transaction getTransaction(TransactionTO transactionTO) {
        if (transactionTO.getAmount() == null || transactionTO.getAmount().signum() <= BigDecimal.ZERO.intValue()) {
            throw new BusinessException(PismoDemoMessageCode.ERROR_AMOUNT_LESS_THEN_ZERO);
        }

        Transaction transaction = new Transaction();
        Account account = accountService.getById(transactionTO.getAccountId());
        transaction.setAccount(account);
        transaction.setOperationType(OperationType.getById(transactionTO.getOperationType()));
        transaction.setEventDate(LocalDateTime.now());

        if (transaction.getOperationType().equals(OperationType.PAGAMENTO)) {
            transaction.setAmount(transactionTO.getAmount());
        } else {
            transaction.setAmount(transactionTO.getAmount().negate());
        }

        return transaction;
    }
}
