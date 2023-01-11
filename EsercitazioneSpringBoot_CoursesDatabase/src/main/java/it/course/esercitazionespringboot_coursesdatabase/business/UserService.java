package it.course.esercitazionespringboot_coursesdatabase.business;

import it.course.esercitazionespringboot_coursesdatabase.models.ERole;
import it.course.esercitazionespringboot_coursesdatabase.models.Role;
import it.course.esercitazionespringboot_coursesdatabase.models.User;
import it.course.esercitazionespringboot_coursesdatabase.payload.request.SignupRequest;
import it.course.esercitazionespringboot_coursesdatabase.repositories.RoleRepository;
import it.course.esercitazionespringboot_coursesdatabase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public Iterable<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User getUser(long id) {
        return this.userRepository.getReferenceById(id);
    }

    public User insertUser(User user) {
        return this.userRepository.save(user);
    }

    public User registerUser(@RequestBody SignupRequest signUpRequest, ERole role) {
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                this.encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role roleAdmin = this.roleRepository.findByName(ERole.valueOf(role.name())).get();
        roles.add(roleAdmin);

        user.setRoles(roles);

        return user;
    }

    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

}
