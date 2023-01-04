package it.course.esercitazionespringboot_coursesdatabase.services;

import it.course.esercitazionespringboot_coursesdatabase.models.Course;
import it.course.esercitazionespringboot_coursesdatabase.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseRepository getCourseRepository() {
        return this.courseRepository;
    }

    public Iterable<Course> getCourses() {
        return this.courseRepository.findAll();
    }

    public Optional<Course> getCourse(long id) {
        return this.courseRepository.findById(id);
    }

    public Course insertCourse(Course course) {
        return this.courseRepository.save(course);
    }

    public void deleteCourse(long id) {
        this.courseRepository.deleteById(id);
    }

}
