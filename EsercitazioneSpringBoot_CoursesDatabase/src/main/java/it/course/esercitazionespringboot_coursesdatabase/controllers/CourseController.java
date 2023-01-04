package it.course.esercitazionespringboot_coursesdatabase.controllers;

import it.course.esercitazionespringboot_coursesdatabase.models.Course;
import it.course.esercitazionespringboot_coursesdatabase.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
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

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course modifiedCourse) {
        Course course = this.courseService.getCourse(id).get();
        course.setName(modifiedCourse.getName());
        course.setDescription(modifiedCourse.getDescription());
        return new ResponseEntity<>(this.courseService.insertCourse(course), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") long id) {
        this.courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
