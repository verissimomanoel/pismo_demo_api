/*
 * AccountService.java
 * Copyright MedSchool.
 *
 * Este software é confidencial e propriedade da MedSchool.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem expressa autorização da MedSchool.
 * Este arquivo contém informações proprietárias.
 */
package br.com.pismo.demo.api.account;

/**
 * Interface of business class of {@link Account}.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
public interface AccountService {
    /**
     * Save data of entity {@link Account}.
     *
     * @param account
     * @return
     */
     Account save(Account account);

    /**
     * Get account by id reported.
     *
     * @param id
     * @return
     */
     Account getById(String id);
}
