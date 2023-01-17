package it.course.esercitazionespringboot_coursesdatabase.controllers;

import it.course.esercitazionespringboot_coursesdatabase.business.CourseBO;
import it.course.esercitazionespringboot_coursesdatabase.business.RoleBO;
import it.course.esercitazionespringboot_coursesdatabase.business.UserBO;
import it.course.esercitazionespringboot_coursesdatabase.business.impl.CourseService;
import it.course.esercitazionespringboot_coursesdatabase.business.impl.RoleService;
import it.course.esercitazionespringboot_coursesdatabase.business.impl.UserService;
import it.course.esercitazionespringboot_coursesdatabase.models.Course;
import it.course.esercitazionespringboot_coursesdatabase.models.ERole;
import it.course.esercitazionespringboot_coursesdatabase.models.Role;
import it.course.esercitazionespringboot_coursesdatabase.models.User;
import it.course.esercitazionespringboot_coursesdatabase.payload.request.SignupRequest;
import it.course.esercitazionespringboot_coursesdatabase.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/apiUser")
public class UserController {

    private final UserBO userBO;

    private final CourseBO courseBO;

    private final RoleBO roleBO;

    @Autowired
    public UserController(UserBO userBO, CourseBO courseBO, RoleBO roleBO) {
        this.userBO = userBO;
        this.courseBO = courseBO;
        this.roleBO = roleBO;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        if (this.userBO.findById(id).isPresent()) {
            return new ResponseEntity<>(this.userBO.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>((ArrayList<User>)this.userBO.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/courses/{idUser}")
    public ResponseEntity<Set<Course>> getCoursesPerUser(@PathVariable("idUser") Integer idUser) {
        if (this.userBO.findById(idUser).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<Course> coursesPerUser = this.userBO.findById(idUser).get().getCourses();
        if (coursesPerUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(coursesPerUser, HttpStatus.OK);
    }

    @GetMapping("/users/{role}")
    public ResponseEntity<List<User>> getUserPerRole(@PathVariable String role) {
        if (this.roleBO.findByRole(ERole.valueOf(role)) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Role _role = this.roleBO.findByRole(ERole.valueOf(role)).get();
        List<User> usersPerRole = new ArrayList<>();
        this.userBO.findAllUsers().forEach(user ->
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
        User user = this.userBO.registerUser(signUpRequest, ERole.ROLE_ADMIN);
        this.userBO.save(user);
        return new ResponseEntity<>(new MessageResponse("Admin registered successfully!"), HttpStatus.OK);
    }

    @PostMapping("/insert/mod")
    public ResponseEntity<MessageResponse> insertUserMod(@RequestBody SignupRequest signUpRequest) {
        User user = this.userBO.registerUser(signUpRequest, ERole.ROLE_MODERATOR);
        this.userBO.save(user);
        return new ResponseEntity<>(new MessageResponse("Moderator registered successfully!"), HttpStatus.OK);
    }

    @PostMapping("/insert/user")
    public ResponseEntity<MessageResponse> insertUser(@RequestBody SignupRequest signUpRequest) {
        User user = this.userBO.registerUser(signUpRequest, ERole.ROLE_USER);
        this.userBO.save(user);
        return new ResponseEntity<>(new MessageResponse("User registered successfully!"), HttpStatus.OK);
    }

    @PostMapping("/insert/{idUser}/role/{newRole}")
    public ResponseEntity<?> addRoleToUser(@PathVariable("idUser") long idUser,
                                           @PathVariable("newRole") String newRole) {
        if (this.userBO.findById(idUser).isEmpty() || newRole == null || newRole.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userBO.findById(idUser).get();
        Role role = this.roleBO.findByRole(ERole.valueOf(newRole)).get();
        if (user.getRoles().contains(role)) {
            return new ResponseEntity<>(new MessageResponse("This user has already this role"), HttpStatus.NOT_ACCEPTABLE);
        }
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        return new ResponseEntity<>(this.userBO.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idUser}/role/{oldRole}")
    public ResponseEntity<?> removeRoleFromUser(@PathVariable("idUser") long idUser,
                                                @PathVariable("oldRole") String oldRole) {
        if (this.userBO.findById(idUser).isEmpty() || oldRole == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userBO.findById(idUser).get();
        Role role = this.roleBO.findByRole(ERole.valueOf(oldRole)).get();
        if (!user.getRoles().contains(role)) {
            return new ResponseEntity<>(new MessageResponse("This user doesn't have already this role"), HttpStatus.NOT_ACCEPTABLE);
        }
        Set<Role> userRoles = user.getRoles();
        userRoles.removeIf(r -> r.getName().equals(role));
        user.setRoles(userRoles);
        return new ResponseEntity<>(this.userBO.save(user), HttpStatus.OK);
    }

    @PostMapping("/user/associate/{idUser}")
    public ResponseEntity<?> associateCourseUser(@PathVariable("idUser") long idUser, @RequestBody Course provvCourse) {
        if (this.courseBO.findByCourse(provvCourse).isEmpty() || this.userBO.findById(idUser).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userBO.findById(idUser).get();
        Course course = this.courseBO.findByCourse(provvCourse).get();

        Set<User> usersCourse = course.getUsers();
        if (usersCourse.contains(course)) {

        }
        usersCourse.add(user);
        course.setUsers(usersCourse);
        this.courseBO.save(course);

        Set<Course> coursesUser = user.getCourses();
        coursesUser.add(course);
        user.setCourses(coursesUser);
        this.userBO.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User modifiedUser) {
        User user = this.userBO.findById(id).get();
        user.setUsername(modifiedUser.getUsername());
        user.setEmail(modifiedUser.getEmail());
        user.setPassword(modifiedUser.getPassword());
        user.setRoles(modifiedUser.getRoles());
        user.setCourses(modifiedUser.getCourses());
        return new ResponseEntity<>(this.userBO.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){
        if (this.userBO.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.userBO.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}