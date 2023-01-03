package it.course.controllers;

import it.course.models.Course;
import it.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Optional<Course>> getCourseById(@PathVariable("id") long id) {
        Optional course = this.courseRepository.findById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/allcourses")
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = new ArrayList<Course>();
        this.courseRepository.findAll().forEach(courses::add);
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<Course> insertCourse(@RequestBody Course course) {
        this.courseRepository.save(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateTutorial(@PathVariable("id") long id, @RequestBody Course updatedCourse) {
        Course course = this.courseRepository.findById(id).get();
        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());
        return new ResponseEntity<>(this.courseRepository.save(course), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") long id) {
        this.courseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
