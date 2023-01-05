package it.course.esercitazionespringboot_coursesdatabase.controllers;

import it.course.esercitazionespringboot_coursesdatabase.business.CourseService;
import it.course.esercitazionespringboot_coursesdatabase.business.RoleService;
import it.course.esercitazionespringboot_coursesdatabase.business.UserService;
import it.course.esercitazionespringboot_coursesdatabase.models.ERole;
import it.course.esercitazionespringboot_coursesdatabase.models.Role;
import it.course.esercitazionespringboot_coursesdatabase.models.User;
import it.course.esercitazionespringboot_coursesdatabase.payload.request.SignupRequest;
import it.course.esercitazionespringboot_coursesdatabase.payload.response.MessageResponse;
import it.course.esercitazionespringboot_coursesdatabase.repositories.CourseRepository;
import it.course.esercitazionespringboot_coursesdatabase.repositories.RoleRepository;
import it.course.esercitazionespringboot_coursesdatabase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
@RestController
@RequestMapping("/api")
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
    public ResponseEntity<User> insertUser(@RequestBody User user) {
        this.userService.insertUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/insertCourse/{id}/{idCourse}")
    public ResponseEntity<HttpStatus> insertCourseIntoUser(@PathVariable("id") long id,
                                                 @PathVariable("idCourse") long idCourse) {
        User user = this.userService.getUser(id).get();
        Course course = this.courseService.getCourse(idCourse).get();
        if ((user == null || course == null) && user.getCourses().contains(course)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<Course> newCourses = user.getCourses();
        newCourses.add(course);
        user.setCourses(newCourses);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/insertRole/{id}/{idRole}")
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

    @GetMapping("/roles/{idUser}")
    public ResponseEntity<User> getRolesPerUser(@PathVariable("idUser") long idUser) {
        User user = this.userService.getUser(idUser).get();
        Set<Role> rolesPerUser = user.getRoles();
        user.setRoles(rolesPerUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
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
*/
@RestController
@RequestMapping("/apiUser")
public class UserController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final CourseRepository courseRepository;

    private final UserService userService;
    private final CourseService courseService;
    private final RoleService roleService;

    private final PasswordEncoder encoder;

    @Autowired
    public UserController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          CourseRepository courseRepository,
                          UserService userService,
                          CourseService courseService,
                          RoleService roleService,
                          PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.courseService = courseService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        User user = this.userService.getUser(id);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>((ArrayList<User>)this.userService.getUsers(),HttpStatus.OK);
    }

    @PostMapping("/insert/admin")
    public ResponseEntity<MessageResponse> insertUserAdmin(@RequestBody SignupRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(),
                             signUpRequest.getEmail(),
                             this.encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role roleAdmin = this.roleRepository.findByName(ERole.ROLE_ADMIN).get();
        roles.add(roleAdmin);

        user.setRoles(roles);
        this.userService.insertUser(user);
        return new ResponseEntity<>(new MessageResponse("Admin registered successfully!"), HttpStatus.OK);
    }

    @PostMapping("/insert/mod")
    public ResponseEntity<MessageResponse> insertUserMod(@RequestBody SignupRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                this.encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role roleMod = this.roleRepository.findByName(ERole.ROLE_MODERATOR).get();
        roles.add(roleMod);

        user.setRoles(roles);
        this.userService.insertUser(user);
        return new ResponseEntity<>(new MessageResponse("Moderator registered successfully!"), HttpStatus.OK);
    }

    @PostMapping("/insert/user")
    public ResponseEntity<MessageResponse> insertUser(@RequestBody SignupRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                this.encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role roleUser = this.roleRepository.findByName(ERole.ROLE_USER).get();
        roles.add(roleUser);

        user.setRoles(roles);
        this.userService.insertUser(user);
        return new ResponseEntity<>(new MessageResponse("User registered successfully!"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){
        this.userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}