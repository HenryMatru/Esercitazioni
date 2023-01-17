package it.course.esercitazionespringboot_coursesdatabase.repositories;

import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import it.course.esercitazionespringboot_coursesdatabase.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Iterable<Exam> getExamByResult(Integer result);
}
