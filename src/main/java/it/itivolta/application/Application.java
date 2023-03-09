package it.itivolta.application;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "itiblog")
@PWA(name = "ITIDB", shortName = "ITIDB", offlinePath="offline.html", offlineResources = { "./images/offline.png"})
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10") // Specifica un package npm da usare in questo componente
public class Application implements AppShellConfigurator {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
