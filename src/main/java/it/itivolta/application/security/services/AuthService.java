package it.itivolta.application.security.services;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.VaadinSession;
import it.itivolta.application.components.NotificationError;
import it.itivolta.application.data.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AuthService {
    private static final String LOGOUT_SUCCESS_URL = "/login";
    private static final String REGISTER_SUCCESS_URL = "/login";
    private static final String LOGIN_SUCCESS_URL = "/";

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void authenticate(String username, String password) {
        User user = userRepository.getByUsername(username);
        if (user != null && checkPassword(user, password)) {
            VaadinSession.getCurrent().setAttribute(User.class, user);
            Collection<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("USER"));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, password, roles));
            UI.getCurrent().getPage().setLocation(LOGIN_SUCCESS_URL);
        } else {
            NotificationError notificationError = new NotificationError();
            notificationError.show("Username or password not valid");
        }
    }

    public boolean checkPassword(User user, String password) {
        return BCrypt.checkpw(password, user.getPasswordHash());
    }

    public void register(User user) {
        if (emailExists(user.getEmail())) {
            NotificationError notificationError = new NotificationError();
            notificationError.show("Email already registered! Try again.");
            return;
        }
        userRepository.save(new User(user.getUsername(), user.getEmail(), user.getPassword()));
        UI.getCurrent().getPage().setLocation(REGISTER_SUCCESS_URL);
    }

    private boolean emailExists(String email) {
        return userRepository.getByEmail(email) != null;
    }

    public void logout() {
        UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
        // Instanzia il servizio per il logout
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        //Esegui il logout
        logoutHandler.logout(
                VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                null);
    }
}
