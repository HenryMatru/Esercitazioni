package it.course.esercitazionespringboot_coursesdatabase.business.impl;

import it.course.esercitazionespringboot_coursesdatabase.business.UserBO;
import it.course.esercitazionespringboot_coursesdatabase.models.Course;
import it.course.esercitazionespringboot_coursesdatabase.models.ERole;
import it.course.esercitazionespringboot_coursesdatabase.models.Role;
import it.course.esercitazionespringboot_coursesdatabase.models.User;
import it.course.esercitazionespringboot_coursesdatabase.payload.request.SignupRequest;
import it.course.esercitazionespringboot_coursesdatabase.repositories.CourseRepository;
import it.course.esercitazionespringboot_coursesdatabase.repositories.RoleRepository;
import it.course.esercitazionespringboot_coursesdatabase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class UserService implements UserBO {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final CourseRepository courseRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       CourseRepository courseRepository,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
        this.encoder = encoder;
    }

    @Override
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

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void update(User user) {
        this.userRepository.save(user);
    }

    @Override
    public Optional<User> findById(long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public List<User> findByRole(Role role) {
        List<User> usersPerRole = new ArrayList<>();
        for (User u : this.userRepository.findAll()) {
            u.getRoles().contains(role);
            usersPerRole.add(u);
        }
        return usersPerRole;
    }

    @Override
    public Iterable<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        for (User user : this.userRepository.findAll()) {
            if (user.getUsername().equals(username)) {
                return this.userRepository.findById(user.getId());
            }
        }
        return null;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return this.findByUsername(username).isPresent();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        for (User user : this.userRepository.findAll()) {
            if (user.getEmail().equals(email)) {
                return this.userRepository.findById(user.getId());
            }
        }
        return null;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return this.findByEmail(email).isPresent();
    }

    @Override
    public void delete(long id) {
        for (Course course : this.courseRepository.findAll()) {
            course.getUsers().removeIf(u -> u.getId() == this.userRepository.findById(id).get().getId());
        }
        for (Role role : this.roleRepository.findAll()) {
            role.getUsers().removeIf(u -> u.getId() == this.userRepository.findById(id).get().getId());
        }
        this.userRepository.deleteById(id);
    }

}
