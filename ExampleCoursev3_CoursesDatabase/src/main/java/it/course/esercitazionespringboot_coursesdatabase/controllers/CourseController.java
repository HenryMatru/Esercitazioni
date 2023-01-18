package it.course.esercitazionespringboot_coursesdatabase.controllers;

import it.course.esercitazionespringboot_coursesdatabase.business.CourseBO;
import it.course.esercitazionespringboot_coursesdatabase.business.UserBO;
import it.course.esercitazionespringboot_coursesdatabase.business.impl.CourseService;
import it.course.esercitazionespringboot_coursesdatabase.business.impl.UserService;
import it.course.esercitazionespringboot_coursesdatabase.models.Course;
import it.course.esercitazionespringboot_coursesdatabase.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/apiCourse")
public class CourseController {

    private final UserBO userBO;

    private final CourseBO courseBO;

    @Autowired
    public CourseController(UserBO userBO,
                            CourseBO courseBO) {
        this.userBO = userBO;
        this.courseBO = courseBO;
    }

    @PostMapping("/insert")
    public ResponseEntity<Course> insertCourse(@RequestBody Course course) {
        return new ResponseEntity<>(this.courseBO.save(course), HttpStatus.CREATED);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") long id) {
        Course course = this.courseBO.findById(id).get();
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/course")
    public ResponseEntity<ArrayList<Course>> getAllCourse() {
        ArrayList<Course> courses = (ArrayList<Course>) this.courseBO.findAllCourses();
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<HttpStatus> deleteCourse(@PathVariable long id) {
        if (this.courseBO.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.courseBO.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course modifiedCourse) {
        if (this.courseBO.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Course course = this.courseBO.findById(id).get();
        course.setName(modifiedCourse.getName());
        return new ResponseEntity<>(this.courseBO.save(course), HttpStatus.OK);
    }

    @PostMapping("/user/{idUser}/{idCourse}")
    public ResponseEntity<Course> createCourseUser(@PathVariable("idUser") long idUser,
                                                   @PathVariable("idCourse") long idCourse) {
        if (this.userBO.findById(idUser).isEmpty() || this.courseBO.findById(idCourse).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userBO.findById(idUser).get();
        Course course = this.courseBO.findById(idCourse).get();
        Set<User> userSet = new HashSet<>();
        userSet.add(user);
        course.setUsers(userSet);
        return new ResponseEntity<>(this.courseBO.save(course), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{idCourse}/{idUser}")
    public ResponseEntity<HttpStatus> deleteUserFromCourse(@PathVariable("idCourse") long idCourse,
                                                           @PathVariable("idUser") long idUser) {
        if (this.userBO.findById(idUser).isEmpty() || this.courseBO.findById(idCourse).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Course course = this.courseBO.findById(idCourse).get();
        course.getUsers().removeIf(u -> u.getId() == this.userBO.findById(idUser).get().getId());
        this.courseBO.save(course);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<Map<String,String>> uploadFile(@PathVariable Long id ,@RequestParam("file") MultipartFile data) {
        try {
            this.courseBO.uploadFile(id,data);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String,String> map = new HashMap<>();
            String message = "Non posso caricare il file: " + data.getOriginalFilename();
            map.put("Error",message);
            return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);

        }
    }
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Course _course = this.courseBO.findByIdFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + _course.getName() + "\"")
                .body(_course.getData());
    }

    @PutMapping("/delete/file/{id}")
    public ResponseEntity<HttpStatus> deleteFile(@PathVariable Long id) {
        try {
            this.courseBO.deleteFile(id);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
