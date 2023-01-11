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

    private final UserService userService;

    private final CourseService courseService;

    @Autowired
    public CourseController(UserService userService,
                            CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Course> insertCourse(@RequestBody Course newCourse) {
        Course _course = this.courseService.insertCourse(newCourse);
        return new ResponseEntity<>(_course, HttpStatus.CREATED);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") long id) {
        Course course = this.courseService.getCourse(id).get();
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/course")
    public ResponseEntity<ArrayList<Course>> getAllCourse() {
        ArrayList<Course> courses = (ArrayList<Course>) this.courseService.getCourses();
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<HttpStatus> deleteCourse(@PathVariable long id) {
        if (this.courseService.getCourse(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course modifiedCourse) {
        if (this.courseService.getCourse(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Course course = this.courseService.getCourse(id).get();
        course.setName(modifiedCourse.getName());
        return new ResponseEntity<>(this.courseService.insertCourse(course), HttpStatus.OK);
    }

    @PostMapping("/user/{idUser}/{idCourse}")
    public ResponseEntity<Course> createCourseUser(@PathVariable("idUser") long idUser,
                                                   @PathVariable("idCourse") long idCourse) {
        if (this.userService.getUser(idUser) == null || this.courseService.getCourse(idCourse) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userService.getUser(idUser);
        Course course = this.courseService.getCourse(idCourse).get();
        Set<User> userSet = new HashSet<>();
        userSet.add(user);
        course.setUsers(userSet);
        Course _course = this.courseService.insertCourse(course);
        return new ResponseEntity<>(_course, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{idCourse}/{idUser}")
    public ResponseEntity<HttpStatus> deleteUserFromCourse(@PathVariable("idCourse") long idCourse,
                                                           @PathVariable("idUser") long idUser) {
        if (this.courseService.getCourse(idCourse) == null || this.userService.getUser(idUser) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Course course = this.courseService.getCourse(idCourse).get();
        course.getUsers().removeIf(u -> u.getId() == this.userService.getUser(idUser).getId());
        this.courseService.insertCourse(course);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
