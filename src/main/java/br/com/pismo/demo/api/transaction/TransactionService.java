/*
 * TransactionService.java
 * Copyright MedSchool.
 *
 * Este software é confidencial e propriedade da MedSchool.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem expressa autorização da MedSchool.
 * Este arquivo contém informações proprietárias.
 */
package br.com.pismo.demo.api.transaction;

import br.com.pismo.demo.api.account.Account;
import br.com.pismo.demo.api.exception.BusinessException;

/**
 * Interface of business class of {@link Transaction}.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
public interface TransactionService {
    /**
     * Save data of entity {@link Transaction}.
     *
     * @param transaction
     * @return
     * @throws BusinessException
     */
    Transaction save(Transaction transaction);

    /**
     * Create a instance of {@link Transaction} by {@link TransactionTO}.
     *
     * @param transactionTO
     * @return
     */
    Transaction getTransaction(TransactionTO transactionTO);
}
