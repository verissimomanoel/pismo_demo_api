/*
 * MessageCode.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.exception;

import java.io.Serializable;

/**
 * Interface responsible by define a contract with a code messages.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
public interface MessageCode extends Serializable {

    /**
     * Return a code of message in *.properties.
     *
     * @return
     */
    public String getCode();

    /**
     * Return the HTTP Status code.
     *
     * @return
     */
    public Integer getStatus();
}
