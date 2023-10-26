
package com.example.gatewayserver.Config;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
@KeycloakConfiguration
@RequiredArgsConstructor
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@Configuration
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api-gateway/**").hasRole("admin")
                .anyRequest().authenticated();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}

/*
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(KeycloakSecurityConfig.class);

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(keycloakAuthenticationProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        super.configure(http);
        http.csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api-gateway/**").hasRole("admin")
                .anyRequest().authenticated();
*//*

*/
/*
super.configure(http);
        http.csrf()
                .disable()
                .httpBasic()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/*").hasAuthority("user")
                .and()
                .authorizeRequests()
                .antMatchers("/candidats/user/*").hasAuthority("user")
                .and()
                .authorizeRequests()
                .antMatchers("/candidats/admin/**").hasAuthority("admin")
                .anyRequest()
                .authenticated();

*//*

  */
/*  }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager()throws Exception{
        return super.authenticationManager();


    }
    public void someMethod() {
        // Ajoutez des lignes de journalisation
        logger.debug("Ceci est un message de débogage");
        logger.info("Ceci est un message d'information");
        logger.warn("Ceci est un avertissement");
        logger.error("Ceci est une erreur");
    }

}
*/
