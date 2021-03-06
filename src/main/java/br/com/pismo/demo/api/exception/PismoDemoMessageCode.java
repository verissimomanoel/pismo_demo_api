/*
 * AuthMessageCode.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.exception;

/**
 * Enum with the codes of exceptions and business messages.
 *
 * @author Manoel Veríssimo dos Santos Neto S/A.
 */
public enum PismoDemoMessageCode implements MessageCode {
    ERROR_EMPTY_FIELD("value.empty", 400),

    ERROR_UNEXPECTED("ME001", 500),
    ERROR_REQUIRED_FIELDS("ME002", 400),
    ERROR_LOGIN_PASSWORD_INVALID("ME003", 400),
    ERROR_TOKEN_INVALID("ME004", 401),
    ERROR_NO_FOUND("ME005", 404),
    ERROR_ACCOUNT_NO_FOUND("ME006", 404),
    ERROR_USER_ALREADY_EXISTS("ME007", 400),
    ERROR_FILTER_REQUIRED("ME008", 400),
    ERROR_ACCOUNT_ALREADY_EXISTS("ME009", 400),
    ERROR_OPERATION_TYPE_NOT_FOUND("ME010", 400),
    ERROR_AMOUNT_LESS_THEN_ZERO("ME011", 400);

    private final String code;

    private final Integer status;

    /**
     * Constructor of class.
     *
     * @param code
     * @param status
     */
    PismoDemoMessageCode(final String code, final Integer status) {
        this.code = code;
        this.status = status;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @see Enum#toString()
     */
    @Override
    public String toString() {
        return code;
    }
}
