package it.course.esercitazionespringboot_coursesdatabase.business;

import it.course.esercitazionespringboot_coursesdatabase.models.Course;
import it.course.esercitazionespringboot_coursesdatabase.models.Exam;

import java.util.List;
import java.util.Optional;

public interface ExamBO {

    public Exam save(Exam exam);

    public Optional<Exam> findById(long id);

    public Optional<Exam> findByExam(Exam exam);

    public List<Exam> findAllExams();

    public List<Exam> findAllExams(int result);

    public void update(Exam exam);

    public void delete(long id);

}
