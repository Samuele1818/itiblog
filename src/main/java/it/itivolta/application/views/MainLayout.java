package it.itivolta.application.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import it.itivolta.application.security.services.AuthService;

public class MainLayout extends AppLayout {
    private final AuthService authService;

    public MainLayout(AuthService authService) {
        this.authService = authService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("itiblog"); // Crea un nuovo h1
        logo.addClassNames("text-l", "m-m"); // dagli le clssi test large e margin medium

        Button logout = new Button("Esci", e -> authService.logout()); //bottone logout

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout); // crea il contenitore orizzntale

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER); // item posizionato centralmente
        header.expand(logo); // il logo prende tutto lo spazio extra del layout
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m"); // aggiungi padding

        addToNavbar(header); // aggiungi header alla navbar e cambia il contenuto della navbar
    }

    private void createDrawer() { //link verso le altre viste
        RouterLink listLink = new RouterLink("Home", MainView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation()); // Imposta l'highlight se il route corrente è uguale a quello del link

        addToDrawer(new VerticalLayout(
                listLink
        )); // aggiungi la links list al drawer e cambia il contenuto del drawer
    }
}
