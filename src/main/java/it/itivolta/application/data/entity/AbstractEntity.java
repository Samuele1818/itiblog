package it.itivolta.application.data.entity;

import org.hibernate.annotations.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {

    // Genera un UUID casuale di caratteri che fa da ID all'entità
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        // Ritorna l'hash dell'ID se l'ID esiste
        if (id != null) {
            return id.hashCode();
        }
        return super.hashCode(); // Ritorna l'hash dell'oggetto stesso
    }

    // Ridefinisci il metod equals
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEntity)) { // Controlla se l'oggetto passato è un instanza dell'interfaccia AbstractEbtity
            return false;
        }
        AbstractEntity other = (AbstractEntity) obj;

        if (id != null) {
            return id.equals(other.id); // Se esiste l'ID controlla l'uguaglinza tramite esso
        }
        return super.equals(other); // controlla l'uguaglianza dei due oggetti
    }
}
