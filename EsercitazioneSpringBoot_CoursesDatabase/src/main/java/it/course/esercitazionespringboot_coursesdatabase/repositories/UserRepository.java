package it.course.esercitazionespringboot_coursesdatabase.repositories;

import it.course.esercitazionespringboot_coursesdatabase.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
