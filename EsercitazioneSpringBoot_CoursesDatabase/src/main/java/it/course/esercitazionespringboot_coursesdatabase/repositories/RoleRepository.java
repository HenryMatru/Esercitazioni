package it.course.esercitazionespringboot_coursesdatabase.repositories;

import it.course.esercitazionespringboot_coursesdatabase.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
