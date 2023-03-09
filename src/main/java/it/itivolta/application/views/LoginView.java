package it.itivolta.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import it.itivolta.application.security.services.AuthService;
import it.itivolta.application.security.services.UserRepository;

@PageTitle("Login")
@Route("login")
public class LoginView extends VerticalLayout {
    AuthService authService;

    TextField username = new TextField("Username");

    PasswordField password = new PasswordField("Password");

    Button login = new Button("Login");

    public LoginView(UserRepository userRepository) { //VISTA LOGIN
        authService = new AuthService(userRepository);

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);


        VerticalLayout header = new VerticalLayout();
        header.add(new H1("Login")); //Header con info
        header.setAlignItems(FlexComponent.Alignment.CENTER);

        login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        login.addClickListener(event -> {
            authService.authenticate(username.getValue(), password.getValue());
        });

        add(header, username, password, login, new RouterLink("Register", RegisterView.class)); // aggiunge i componenti sulla pagina
    }
}
