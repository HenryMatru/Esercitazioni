package it.course.esercitazionespringboot_coursesdatabase.business.impl;

import it.course.esercitazionespringboot_coursesdatabase.business.CourseBO;
import it.course.esercitazionespringboot_coursesdatabase.models.Course;
import it.course.esercitazionespringboot_coursesdatabase.models.Exam;
import it.course.esercitazionespringboot_coursesdatabase.models.User;
import it.course.esercitazionespringboot_coursesdatabase.repositories.CourseRepository;
import it.course.esercitazionespringboot_coursesdatabase.repositories.ExamRepository;
import it.course.esercitazionespringboot_coursesdatabase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements CourseBO {

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    private final ExamRepository examRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository,
                         UserRepository userRepository,
                         ExamRepository examRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.examRepository = examRepository;
    }

    @Override
    public Course findByIdFile(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public void uploadFile(Long id, MultipartFile data) throws IOException {
        Course _course = courseRepository.getReferenceById(id);
        _course.setData(data.getBytes());
        _course.setType(data.getContentType());
        courseRepository.save(_course);
    }

    @Override
    public void deleteFile(Long id) throws IOException {
        Course _course = courseRepository.getReferenceById(id);
        _course.setData(null);
        _course.setType(null);
        this.courseRepository.save(_course);
    }

    @Override
    public Course save(Course course) {
        return null;
    }

    @Override
    public Optional<Course> findById(long id) {
        return this.courseRepository.findById(id);
    }

    public Optional<Course> findByCourse(Course course) {
        for (Course c : this.courseRepository.findAll()) {
            if (c.getName().equals(course.getName())) {
                return Optional.of(c);
            }
        }
        return null;
    }

    @Override
    public List<Course> findAllCourses() {
        return this.courseRepository.findAll();
    }

    @Override
    public void update(Course course) {
        this.courseRepository.save(course);
    }

    @Override
    public void delete(long id) {
        for (User user : this.userRepository.findAll()) {
            user.getCourses().removeIf(c -> c.getId() == this.courseRepository.findById(id).get().getId());
        }

        for (Exam exam : this.examRepository.findAll()) {
            if (exam.getCourse().getId() == this.courseRepository.findById(id).get().getId()) {
                exam.setCourse(null);
            }
        }

        this.courseRepository.deleteById(id);
    }

}
