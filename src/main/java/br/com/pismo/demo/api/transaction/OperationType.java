/*
 * OperationType.java
 * Copyright MedSchool.
 *
 * Este software é confidencial e propriedade da MedSchool.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem expressa autorização da MedSchool.
 * Este arquivo contém informações proprietárias.
 */
package br.com.pismo.demo.api.transaction;

import br.com.pismo.demo.api.exception.BusinessException;
import br.com.pismo.demo.api.exception.PismoDemoMessageCode;

/**
 * The enum to identify the type of transaction.
 *
 * @author MedSchool
 */
public enum OperationType {
    COMPRA_A_VISTA(1, "Compra a Vista"),
    COMPRA_PARCELADA(2, "Compra Parcelada"),
    SAQUE(3, "Saque"),
    PAGAMENTO(4, "Pagamento");

    private final Integer id;

    private final String description;

    /**
     * Constructor of class.
     *
     * @param id
     * @param description
     */
    OperationType(final Integer id, final String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the enum Operation Type by id reported.
     *
     * @param id
     * @return
     */
    public static OperationType getById(Integer id) {
        for (OperationType operationType : OperationType.values()) {
            if (id == operationType.getId()) {
                return operationType;
            }
        }

        throw new BusinessException(PismoDemoMessageCode.ERROR_OPERATION_TYPE_NOT_FOUND);
    }
}
