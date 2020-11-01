/*
 * AccountRepository.java
 * Copyright MedSchool.
 *
 * Este software é confidencial e propriedade da MedSchool.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem expressa autorização da MedSchool.
 * Este arquivo contém informações proprietárias.
 */
package br.com.pismo.demo.api.account;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository to data access of entity {@link Account}
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    /**
     * Search account by number (staring with).
     *
     * @param documentNumber
     * @return
     */
    Optional<Account> findAccountByDocumentNumber(String documentNumber);
}
