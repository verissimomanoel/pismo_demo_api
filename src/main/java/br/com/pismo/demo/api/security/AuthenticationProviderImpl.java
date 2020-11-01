/*
 * AuthenticationProviderImpl.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.security;

import br.com.pismo.demo.api.auth.AuthService;
import br.com.pismo.demo.api.auth.CredentialTO;
import br.com.pismo.demo.api.exception.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Class responsible to provide an instance of {@link Authentication} with user credentials.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private AuthService authService;

    /**
     * @see br.com.pismo.demo.api.security.AuthenticationProvider(String)
     */
    @Override
    public CredentialTO getAuthentication(final String token) {
        CredentialTO credentialTO = null;

        try {
            credentialTO = authService.getInfoByToken(token);
        } catch (BusinessException e) {
            logger.error(HttpStatus.UNAUTHORIZED.getReasonPhrase(), e);
        }

        return credentialTO;
    }
}
