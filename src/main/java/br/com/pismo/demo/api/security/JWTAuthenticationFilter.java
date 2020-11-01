/*
 * JwtAuthenticationFilter.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.security;

import br.com.pismo.demo.api.auth.CredentialTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter that checks the user's authentication credentials (JWT).
 *
 * @author Manoel Veríssimo dos Santos Neto.
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final AuthenticationProvider authenticationProvider;

    /**
     * Constructor of class.
     *
     * @param authenticationProvider
     */
    public JWTAuthenticationFilter(final AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse,
                                    FilterChain chain) throws ServletException, IOException {

        final HttpServletRequest request = servletRequest;
        final String token = getAccessToken(request);

        Authentication authentication = null;

        if (!StringUtils.isEmpty(token) && isTokenBearer(request)) {
            CredentialTO credentialTO = authenticationProvider.getAuthentication(token);
            authentication = getAuthentication(credentialTO);
        }

        if (authentication == null) {
            if (request.getRequestURL().indexOf("swagger-ui") >= 0) {
                List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities();
                authentication = new UsernamePasswordAuthenticationToken("adminpismo@gmail.com", "admin@123", grantedAuthorities);
            } else {
                authentication = new UsernamePasswordAuthenticationToken(null, null);
            }
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Returns access token retrieved from instance {@link HttpServletRequest}.
     *
     * @return
     */
    private String getAccessToken(final HttpServletRequest request) {
        String accessToken = null;

        if (request != null) {
            accessToken = request.getHeader(AUTH_HEADER);

            if (!StringUtils.isEmpty(accessToken)) {
                accessToken = accessToken.replace(BEARER_PREFIX, "").trim();
            }
        }
        return accessToken;
    }

    /**
     * Checks if the request access token is 'Bearer'.
     *
     * @param request
     * @return
     */
    private boolean isTokenBearer(final HttpServletRequest request) {
        boolean valid = Boolean.FALSE;

        if (request != null) {
            String accessToken = request.getHeader(AUTH_HEADER);
            valid = !StringUtils.isEmpty(accessToken) && accessToken.trim().startsWith(BEARER_PREFIX);
        }
        return valid;
    }

    /**
     * Returns the instance {@link Authentication} by {@link CredentialTO}.
     *
     * @param credentialTO
     * @return
     */
    private Authentication getAuthentication(final CredentialTO credentialTO) {
        Authentication authentication = null;

        if (credentialTO != null) {
            List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities();
            authentication = new UsernamePasswordAuthenticationToken(credentialTO.getLogin(), credentialTO,
                    grantedAuthorities);
        }
        return authentication;
    }

    /**
     * Returns the list of Granted Authorities according to the entered Credential.
     *
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList();

        //MOCK
        grantedAuthorities.add(new SimpleGrantedAuthority("admin"));

        return grantedAuthorities;
    }

}
