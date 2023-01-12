package it.course.esercitazionespringboot_coursesdatabase.repositories;

import it.course.esercitazionespringboot_coursesdatabase.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
