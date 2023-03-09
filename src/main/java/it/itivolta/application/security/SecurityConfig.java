package it.itivolta.application.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import it.itivolta.application.views.LoginView;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

@EnableWebSecurity // applica metodi quali filtri http e altre catene di sicurezza
@Configuration
// Indica che la classe contiene un metodo @Bean quindi spring può processare la clssse e generare un bean da usare
public class SecurityConfig extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Autoizza l'accesso  /images/** senza autenticazione
        http.authorizeRequests().antMatchers("/images/**").permitAll();
        // Impost la secuity policy che consente le richieste di Vaadin
        super.configure(http);

        // Imposta la pagina di login
        setLoginView(http, LoginView.class, "/logout");
    }
}