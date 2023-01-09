package it.course.esercitazionespringboot_coursesdatabase.controllers;

import it.course.esercitazionespringboot_coursesdatabase.business.CourseService;
import it.course.esercitazionespringboot_coursesdatabase.business.RoleService;
import it.course.esercitazionespringboot_coursesdatabase.business.UserService;
import it.course.esercitazionespringboot_coursesdatabase.models.Course;
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
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/apiUser")
public class UserController {

    private final UserService userService;

    private final CourseService courseService;

    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, CourseService courseService, RoleService roleService) {
        this.userService = userService;
        this.courseService = courseService;
        this.roleService = roleService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        User user = this.userService.getUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>((ArrayList<User>)this.userService.getUsers(),HttpStatus.OK);
    }

    @GetMapping("/courses/{idUser}")
    public ResponseEntity<Set<Course>> getCoursesPerUser(@PathVariable("idUser") Integer idUser) {
        if (this.userService.getUser(idUser) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<Course> coursesPerUser = this.userService.getUser(idUser).getCourses();
        if (coursesPerUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(coursesPerUser, HttpStatus.OK);
    }

    @GetMapping("/users/{role}")
    public ResponseEntity<List<User>> getUserPerRole(@PathVariable String role) {
        if (this.roleService.getRole(role) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Role _role = this.roleService.getRole(role).get();
        List<User> usersPerRole = new ArrayList<>();
        this.userService.getUsers().forEach(user ->
        {
            for (Role r : user.getRoles()) {
                if (r == _role) {
                    usersPerRole.add(user);
                    break;
                }
            }
        });
        return new ResponseEntity<>(usersPerRole, HttpStatus.OK);
    }

    @PostMapping("/insert/admin")
    public ResponseEntity<MessageResponse> insertUserAdmin(@RequestBody SignupRequest signUpRequest) {
        User user = this.userService.registerUser(signUpRequest, ERole.ROLE_ADMIN);
        this.userService.insertUser(user);
        return new ResponseEntity<>(new MessageResponse("Admin registered successfully!"), HttpStatus.OK);
    }

    @PostMapping("/insert/mod")
    public ResponseEntity<MessageResponse> insertUserMod(@RequestBody SignupRequest signUpRequest) {
        User user = this.userService.registerUser(signUpRequest, ERole.ROLE_MODERATOR);
        this.userService.insertUser(user);
        return new ResponseEntity<>(new MessageResponse("Moderator registered successfully!"), HttpStatus.OK);
    }

    @PostMapping("/insert/user")
    public ResponseEntity<MessageResponse> insertUser(@RequestBody SignupRequest signUpRequest) {
        User user = this.userService.registerUser(signUpRequest, ERole.ROLE_USER);
        this.userService.insertUser(user);
        return new ResponseEntity<>(new MessageResponse("User registered successfully!"), HttpStatus.OK);
    }

    @PostMapping("/insert/{idUser}/role/{newRole}")
    public ResponseEntity<?> addRoleToUser(@PathVariable("idUser") long idUser,
                                           @PathVariable("newRole") String newRole) {
        if (this.userService.getUser(idUser) == null || newRole == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userService.getUser(idUser);
        Role role = this.roleService.getRole(ERole.valueOf(newRole)).get();
        if (user.getRoles().contains(role)) {
            return new ResponseEntity<>(new MessageResponse("This user has already this role"), HttpStatus.NOT_ACCEPTABLE);
        }
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        return new ResponseEntity<>(this.userService.insertUser(user), HttpStatus.OK);
    }

    @PostMapping("/user/{idUser}")
    public ResponseEntity<?> associateCourseUser(@PathVariable("idUser") long idUser, @RequestBody Course provvCourse) {
        if (this.courseService.getCourse(provvCourse).isEmpty() || this.userService.getUser(idUser) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userService.getUser(idUser);
        Course course = this.courseService.getCourse(provvCourse).get();

        Set<User> usersCourse = course.getUsers();
        usersCourse.add(user);
        course.setUsers(usersCourse);
        this.courseService.insertCourse(course);

        Set<Course> coursesUser = user.getCourses();
        coursesUser.add(course);
        user.setCourses(coursesUser);
        this.userService.insertUser(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User modifiedUser) {
        User user = this.userService.getUser(id);
        user.setUsername(modifiedUser.getUsername());
        user.setEmail(modifiedUser.getEmail());
        user.setPassword(modifiedUser.getPassword());
        return new ResponseEntity<>(this.userService.insertUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){
        if (this.userService.getUser(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}