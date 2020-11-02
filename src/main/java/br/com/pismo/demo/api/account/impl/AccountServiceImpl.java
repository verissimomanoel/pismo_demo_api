/*
 * AccountServiceImpl.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.account.impl;

import br.com.pismo.demo.api.account.AccountRepository;
import br.com.pismo.demo.api.account.AccountService;
import br.com.pismo.demo.api.exception.BusinessException;
import br.com.pismo.demo.api.exception.PismoDemoMessageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import br.com.pismo.demo.api.account.Account;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Business class of {@link Account}.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@Service
@Scope("prototype")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    /**
     * @see AccountService#save(Account)
     */
    @Override
    public Account save(Account account) {
        Optional<Account> optionalAccount = accountRepository.findAccountByDocumentNumber(account.getDocumentNumber());
        if (optionalAccount.isPresent()) {
            throw new BusinessException(PismoDemoMessageCode.ERROR_ACCOUNT_ALREADY_EXISTS);
        }

        return accountRepository.save(account);
    }

    /**
     * @see AccountService#getById(String)
     */
    @Override
    public Account getById(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException(PismoDemoMessageCode.ERROR_EMPTY_FIELD);
        }

        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.orElseThrow(() -> new BusinessException(PismoDemoMessageCode.ERROR_ACCOUNT_NO_FOUND));
    }
}
