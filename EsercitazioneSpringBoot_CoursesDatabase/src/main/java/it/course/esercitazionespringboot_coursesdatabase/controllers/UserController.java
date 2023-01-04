package it.course.esercitazionespringboot_coursesdatabase.controllers;

import it.course.esercitazionespringboot_coursesdatabase.models.Course;
import it.course.esercitazionespringboot_coursesdatabase.models.Role;
import it.course.esercitazionespringboot_coursesdatabase.models.User;
import it.course.esercitazionespringboot_coursesdatabase.services.CourseService;
import it.course.esercitazionespringboot_coursesdatabase.services.RoleService;
import it.course.esercitazionespringboot_coursesdatabase.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CourseService courseService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService,
                          CourseService courseService,
                          RoleService roleService) {
        this.userService = userService;
        this.courseService = courseService;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = new ArrayList<>();
        this.userService.getUsers().forEach(users::add);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") long id) {
        return new ResponseEntity<>(this.userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<User> insert(@RequestBody User user) {
        this.userService.insertUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/insert/{id}/{idRole}")
    public ResponseEntity<User> insertRoleIntoUser(@PathVariable("id") Long id,
                                                   @PathVariable("idRole") Integer idRole) {
        if (this.roleService.getRole(idRole) == null || this.userService.getUser(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        User user = this.userService.getUser(id).get();
        Role role = this.roleService.getRole(idRole).get();
        Set<Role> newRoles = user.getRoles();
        newRoles.add(role);
        user.setRoles(newRoles);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User modifiedUser) {
        User user = this.userService.getUser(id).get();
        user.setUsername(modifiedUser.getUsername());
        user.setEmail(modifiedUser.getEmail());
        user.setPassword(modifiedUser.getPassword());
        return new ResponseEntity<>(this.userService.insertUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        if (this.userService.getUser(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        User user = this.userService.getUser(id).get();
        user.getCourses().removeAll(user.getCourses());
        this.userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
