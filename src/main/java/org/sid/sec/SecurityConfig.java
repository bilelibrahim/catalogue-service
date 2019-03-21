package org.sid.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Bilel on 21/03/2019.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bcpe = getBCPE();
        auth.inMemoryAuthentication().withUser("admin").password(bcpe.encode("1234")).roles("ADMIN", "USER");
        auth.inMemoryAuthentication().withUser("user1").password(bcpe.encode("1234")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();// ce n'est pas la peine de vérifier l'existance de CSRF token. CSRF tocken permet de se proteger des attaque XSS.
        //http.authorizeRequests().anyRequest().permitAll();// Permettez toutes les cnxs
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// Ce n'est pas la peine d'utiliser les session a la place on va utiliser JWT (json web toolkit)
        http.authorizeRequests().antMatchers("/categories/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/products/**").hasAuthority("USER");
        http.authorizeRequests().anyRequest().authenticated();// Tout le reste nécessite une authentification.
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    BCryptPasswordEncoder getBCPE(){ // Permet l'encodage des mots de passe.
        return new BCryptPasswordEncoder();
    }
}
