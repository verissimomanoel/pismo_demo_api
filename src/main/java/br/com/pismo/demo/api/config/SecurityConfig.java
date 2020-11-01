/*
 * SecurityConfig.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.config;

import br.com.pismo.demo.api.security.AuthenticationProvider;
import br.com.pismo.demo.api.security.JWTAuthenticationFilter;
import br.com.pismo.demo.api.security.KeyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Configuration class to ensure application security.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${app.api.security.jwt.secret}")
    private String secret;

    @Value("${app.api.security.jwt.issuer}")
    private String issuer;

    @Value("${app.api.security.jwt.expiry}")
    private Long expiry;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    /**
     * Return the instance of {@link KeyToken} according to the parameters in application configuration file.
     *
     * @return
     */
    @Bean
    public KeyToken keyToken() {
        return new KeyToken(secret, issuer, expiry);
    }

    /**
     * @see WebSecurityConfigurerAdapter#configure(HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilter(), BasicAuthenticationFilter.class);
    }

    /**
     * Return the intance of {@link JWTAuthenticationFilter}.
     *
     * @return
     */
    private JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter(authenticationProvider);
    }
}
