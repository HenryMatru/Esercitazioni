package it.course.esercitazionespringboot_coursesdatabase.business.impl;

import it.course.esercitazionespringboot_coursesdatabase.business.ExamBO;
import it.course.esercitazionespringboot_coursesdatabase.models.Exam;
import it.course.esercitazionespringboot_coursesdatabase.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService implements ExamBO {

    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Exam save(Exam exam) {
        return this.examRepository.save(exam);
    }

    @Override
    public Optional<Exam> findById(long id) {
        return this.examRepository.findById(id);
    }

    @Override
    public Optional<Exam> findByExam(Exam exam) {
        for (Exam e : this.examRepository.findAll()) {
            if (e.equals(exam)) {
                return this.examRepository.findById(e.getId());
            }
        }
        return null;
    }

    @Override
    public List<Exam> findAllExams() {
        return this.examRepository.findAll();
    }

    @Override
    public List<Exam> findAllExams(int result) {
        List<Exam> examsByResult = new ArrayList<>();
        for (Exam exam : this.examRepository.findAll()) {
            if (exam.getResult() == result) {
                examsByResult.add(exam);
            }
        }
        return examsByResult;
    }

    @Override
    public void update(Exam exam) {
        this.examRepository.save(exam);
    }

    @Override
    public void delete(long id) {
        Exam exam = this.examRepository.findById(id).get();
        exam.setCourse(null);
        this.examRepository.save(exam);
        this.examRepository.deleteById(id);
    }

}
