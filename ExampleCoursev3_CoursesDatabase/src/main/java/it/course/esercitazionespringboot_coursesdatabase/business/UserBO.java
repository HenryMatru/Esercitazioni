package it.course.esercitazionespringboot_coursesdatabase.business;

import it.course.esercitazionespringboot_coursesdatabase.models.ERole;
import it.course.esercitazionespringboot_coursesdatabase.models.Role;
import it.course.esercitazionespringboot_coursesdatabase.models.User;
import it.course.esercitazionespringboot_coursesdatabase.payload.request.SignupRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface UserBO {

    public User registerUser(@RequestBody SignupRequest signUpRequest, ERole role);

    public User save(User user);

    public void update(User user);

    public Optional<User> findById(long uid);

    public List<User> findByRole(Role role);

    public Iterable<User> findAllUsers();

    public Optional<User> findByUsername(String username);

    public Boolean existsByUsername(String username);

    public Optional<User> findByEmail(String email);

    public Boolean existsByEmail(String email);

    public void delete(long id);

}
