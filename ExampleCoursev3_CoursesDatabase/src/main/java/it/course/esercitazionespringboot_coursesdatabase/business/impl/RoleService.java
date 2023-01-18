package it.course.esercitazionespringboot_coursesdatabase.business.impl;

import it.course.esercitazionespringboot_coursesdatabase.business.RoleBO;
import it.course.esercitazionespringboot_coursesdatabase.models.ERole;
import it.course.esercitazionespringboot_coursesdatabase.models.Role;
import it.course.esercitazionespringboot_coursesdatabase.models.User;
import it.course.esercitazionespringboot_coursesdatabase.repositories.RoleRepository;
import it.course.esercitazionespringboot_coursesdatabase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements RoleBO {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    @Autowired
    public RoleService (RoleRepository roleRepository,
                        UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Role save(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(int id) {
        return this.roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findByRole(ERole role) {
        return this.roleRepository.findByName(role);
    }

    @Override
    public Iterable<Role> findAllRoles() {
        return this.roleRepository.findAll();
    }

    @Override
    public void update(Role role) {
        this.roleRepository.save(role);
    }

    @Override
    public void delete(int id) {
        Optional<Role> roleDb = this.roleRepository.findById(id);
        if (roleDb.isPresent()) {
            Role role = roleDb.get();
            for (User user : this.userRepository.findAll()) {
                user.getRoles().removeIf(r -> r == role);
                this.userRepository.save(user);
            }
            this.roleRepository.deleteById(id);
        }
    }

}
