/*
 * TransactionRepository.java
 * Copyright MedSchool.
 *
 * Este software é confidencial e propriedade da MedSchool.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem expressa autorização da MedSchool.
 * Este arquivo contém informações proprietárias.
 */
package br.com.pismo.demo.api.transaction;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to data access of entity {@link Transaction}
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
