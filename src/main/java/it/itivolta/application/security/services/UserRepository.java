package it.itivolta.application.security.services;

import it.itivolta.application.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getByUsername(String username);
    User getByEmail(String email);
}
