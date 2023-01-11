package it.course.esercitazionespringboot_coursesdatabase.business;

import it.course.esercitazionespringboot_coursesdatabase.models.ERole;
import it.course.esercitazionespringboot_coursesdatabase.models.Role;
import it.course.esercitazionespringboot_coursesdatabase.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Iterable<Role> getRoles() {
        return this.roleRepository.findAll();
    }


    public Optional<Role> getRole(Integer id) {
        return this.roleRepository.findById(id);
    }

    public Optional<Role> getRole(ERole role) {
        return this.roleRepository.findByName(role);
    }

    public Optional<Role> getRole(String role) {
        return this.roleRepository.findByName(ERole.valueOf(role));
    }

    public Role insertRole(Role role) {
        return this.roleRepository.save(role);
    }

    public void deleteRole(Integer id) {
        this.roleRepository.deleteById(id);
    }

}
