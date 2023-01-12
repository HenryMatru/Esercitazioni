package it.course.esercitazionespringboot_coursesdatabase.business;

import it.course.esercitazionespringboot_coursesdatabase.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseBO {

    public Course save(Course course);

    public Optional<Course> findById(long id);

    public Optional<Course> findByCourse(Course course);

    public List<Course> findAllCourses();

    public void update(Course course);

    public void delete(long id);
}
