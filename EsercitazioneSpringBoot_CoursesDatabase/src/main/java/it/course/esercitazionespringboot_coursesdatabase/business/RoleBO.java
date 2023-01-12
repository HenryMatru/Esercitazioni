package it.course.esercitazionespringboot_coursesdatabase.business;

import it.course.esercitazionespringboot_coursesdatabase.models.ERole;
import it.course.esercitazionespringboot_coursesdatabase.models.Role;

import java.util.Optional;

public interface RoleBO {
    public void delete(int id);

    public Iterable<Role> findAllRoles();

    public Optional<Role> findById(int uid);

    public Optional<Role> findByRole(ERole role);

    public Role save(Role role);

    public void update(Role role);

}
