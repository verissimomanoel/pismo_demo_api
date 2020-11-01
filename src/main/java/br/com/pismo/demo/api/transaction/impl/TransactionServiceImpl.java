/*
 * TransactionServiceImpl.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.transaction.impl;

import br.com.pismo.demo.api.account.Account;
import br.com.pismo.demo.api.account.AccountService;
import br.com.pismo.demo.api.transaction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
     * @see TransactionService#save(TransactionTO)
     */
    @Override
    public Transaction save(TransactionTO transactionTO) {
        Transaction transaction = new Transaction();
        Account account = accountService.getById(transactionTO.getAccountId());
        transaction.setAccount(account);
        transaction.setOperationType(OperationType.getById(transactionTO.getOperationType()));
        transaction.setEventDate(transactionTO.getEventDate());
        transaction.setAmount(transactionTO.getAmount());

        return transactionRepository.save(transaction);
    }
}
