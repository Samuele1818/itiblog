package it.itivolta.application.data.entity;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User extends AbstractEntity {
    @NotEmpty
    @Size(min = 4, max = 12, message = "Username must be from 4 to 12 characters")
    private String username = "";
    @Email
    @NotEmpty(message = "Please, insert a valid email")
    private String email = "";
    @NotEmpty
    @Transient
    @Size(min = 4, max = 32, message = "Password must be from 4 to 32 characters")
    private String password = "";
    private String passwordHash;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User() {
    }

    @Override // Ridefinisci il metodo toString
    public String toString() {
        return username + " " + email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
