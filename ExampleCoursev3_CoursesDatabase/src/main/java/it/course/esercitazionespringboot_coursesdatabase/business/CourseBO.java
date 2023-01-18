package it.course.esercitazionespringboot_coursesdatabase.business;

import it.course.esercitazionespringboot_coursesdatabase.models.Course;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CourseBO {

    Course findByIdFile(Long id);

    void uploadFile(Long id, MultipartFile data) throws IOException;

    void deleteFile(Long id) throws IOException;

    public Course save(Course course);

    public Optional<Course> findById(long id);

    public Optional<Course> findByCourse(Course course);

    public List<Course> findAllCourses();

    public void update(Course course);

    public void delete(long id);
}
