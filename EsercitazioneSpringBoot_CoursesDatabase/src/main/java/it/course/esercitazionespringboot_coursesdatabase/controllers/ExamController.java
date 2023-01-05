package it.course.esercitazionespringboot_coursesdatabase.controllers;

import it.course.esercitazionespringboot_coursesdatabase.business.ExamService;
import it.course.esercitazionespringboot_coursesdatabase.models.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/apiExam")
public class ExamController {

    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertExam(@RequestBody Exam exam) {
        this.examService.insertExam(exam);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @GetMapping("/exams")
    public ResponseEntity<List<Exam>> getExams() {
        List<Exam> exams = this.examService.getExams();
        if (exams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(this.examService.getExams(), HttpStatus.OK);
    }

    @GetMapping("/exams/{result}")
    public ResponseEntity<List<Exam>> getExamsByResult(@PathVariable Integer result) {
        List<Exam> exams = this.examService.getExams(result);
        if (exams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(this.examService.getExams(result), HttpStatus.OK);
    }


}
