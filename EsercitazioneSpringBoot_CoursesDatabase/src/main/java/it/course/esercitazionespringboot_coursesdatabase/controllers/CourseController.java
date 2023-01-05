package it.course.esercitazionespringboot_coursesdatabase.controllers;

import it.course.esercitazionespringboot_coursesdatabase.business.CourseService;
import it.course.esercitazionespringboot_coursesdatabase.business.RoleService;
import it.course.esercitazionespringboot_coursesdatabase.business.UserService;
import it.course.esercitazionespringboot_coursesdatabase.models.Course;
import it.course.esercitazionespringboot_coursesdatabase.models.User;
import it.course.esercitazionespringboot_coursesdatabase.repositories.CourseRepository;
import it.course.esercitazionespringboot_coursesdatabase.repositories.RoleRepository;
import it.course.esercitazionespringboot_coursesdatabase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/apiCourse")
public class CourseController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final CourseRepository courseRepository;

    private final UserService userService;
    private final CourseService courseService;
    private final RoleService roleService;

    private final PasswordEncoder encoder;

    @Autowired
    public CourseController(UserRepository userRepository,
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

    @PostMapping("/insert")
    public ResponseEntity<Course> insertCourse(@RequestBody Course newCourse) {
        Course course = courseRepository.save(newCourse);
        return new ResponseEntity<Course>(course, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional> getCourse(@PathVariable("id") Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return new ResponseEntity<Optional>(course, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/courses")
    public ResponseEntity<ArrayList<Course>> getAllCourse() {
        ArrayList<Course> courses = (ArrayList<Course>) courseRepository.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<HttpStatus> deleteCourse(@PathVariable long id){
        courseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateRoles(@PathVariable("id") long id, @RequestBody Course modifiedCourse) {
        Course course = courseRepository.findById(id).get();
        course.setName(modifiedCourse.getName());
        return new ResponseEntity<>(courseRepository.save(course), HttpStatus.OK);
    }

    @PostMapping("/user/{id}/course")
    public ResponseEntity<Course> createCourseUser(@PathVariable long id, @RequestBody Course course) {
        User user = this.userService.getUser(id); // userRepository.getReferenceById(id);
        Set<User> userSet = new HashSet<>();
        userSet.add(user);
        course.setUsers(userSet);
        Course _course = courseRepository.save(course);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    private final CourseService courseService;
    private final UserService userService;

    @Autowired
    public CourseController(CourseService courseService,
                            UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = new ArrayList<Course>();
        this.courseService.getCourses().forEach(courses::add);
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Optional<Course>> getCourse(@PathVariable("id") long id) {
        return new ResponseEntity<>(this.courseService.getCourse(id), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<Course> insertCourse(@RequestBody Course course) {
        this.courseService.insertCourse(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PostMapping("/insert/{id}/{idUser}")
    public ResponseEntity<Course> insertUserToCourse(@PathVariable("id") Long id,
                                                     @PathVariable("idUser") Long idUser) {
        User user = this.userService.getUser(idUser).get();
        Course course = this.courseService.getCourse(id).get();
        Set<User> newUsers = new HashSet<>();
        newUsers.add(user);
        course.setUsers(newUsers);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course modifiedCourse) {
        Course course = this.courseService.getCourse(id).get();
        course.setName(modifiedCourse.getName());
        course.setDescription(modifiedCourse.getDescription());
        course.setUsers(modifiedCourse.getUsers());
        return new ResponseEntity<>(this.courseService.insertCourse(course), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") long id) {
        this.courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}/{idUser}")
    public ResponseEntity<HttpStatus> deleteUserFromCourse(@PathVariable("id") long id,
                                                           @PathVariable("idUser") Long idUser) {
        Course course = this.courseService.getCourse(id).get();
        course.getUsers().removeIf(u -> u.getId() == this.userService.getUser(idUser).get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
     */
}
