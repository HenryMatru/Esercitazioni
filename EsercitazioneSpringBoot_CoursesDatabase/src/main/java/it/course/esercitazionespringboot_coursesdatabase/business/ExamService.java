package it.course.esercitazionespringboot_coursesdatabase.business;

import it.course.esercitazionespringboot_coursesdatabase.models.Exam;
import it.course.esercitazionespringboot_coursesdatabase.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public void insertExam(Exam exam) {
        this.examRepository.save(exam);
    }

    public Exam getExam(long id) {
        return this.examRepository.getReferenceById(id);
    }

    public void deleExam(long id) {
        this.examRepository.deleteById(id);
    }

    public List<Exam> getExams() {
        return this.examRepository.findAll();
    }

    public List<Exam> getExams(Integer result) {
        return this.examRepository.findAll().stream().filter(e -> e.getResult() == result).toList();
    }

}
