package it.itivolta.application.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import it.itivolta.application.components.NotificationError;
import it.itivolta.application.data.entity.User;
import it.itivolta.application.security.services.AuthService;
import it.itivolta.application.security.services.UserRepository;

@PageTitle("Register")
@Route("register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {
    AuthService authService;
    TextField username = new TextField("Username");
    EmailField email = new EmailField("Email");
    PasswordField password = new PasswordField("Password");
    Button register = new Button("Register");
    Binder<User> binder = new BeanValidationBinder<>(User.class);
    private User user;

    public RegisterView(UserRepository userRepository) {
        user = new User();
        VerticalLayout header = new VerticalLayout();
        header.add(new H1("Register")); //Header con info
        header.setAlignItems(FlexComponent.Alignment.CENTER);

        authService = new AuthService(userRepository);
        register.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        register.addClickShortcut(Key.ENTER);
        register.addClickListener(event -> validateAndSave());

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(
                header, username, email, password, register
        );

        binder.addStatusChangeListener(event -> register.setEnabled(binder.isValid()));
        binder.bindInstanceFields(this);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(user); // Instanzia le informazioni del form nell'instanza del contatto
            authService.register(user);
        } catch (ValidationException e) {
            NotificationError notificationError = new NotificationError();
            notificationError.show("An error occurred, please try again!");
        }
    }

    public void setUser(User user) {
        this.user = user;
        binder.readBean(user); // Leggi le informazioni dell'oggetto
    }
}