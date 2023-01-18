package it.course.esercitazionespringboot_coursesdatabase.controllers;

import it.course.esercitazionespringboot_coursesdatabase.business.CourseBO;
import it.course.esercitazionespringboot_coursesdatabase.business.ExamBO;
import it.course.esercitazionespringboot_coursesdatabase.business.impl.CourseService;
import it.course.esercitazionespringboot_coursesdatabase.business.impl.ExamService;
import it.course.esercitazionespringboot_coursesdatabase.models.Course;
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

    private final ExamBO examBO;

    private final CourseBO courseBO;

    @Autowired
    public ExamController(ExamBO examBO, CourseBO courseBO) {
        this.examBO = examBO;
        this.courseBO = courseBO;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertExam(@RequestBody Exam exam) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/exams")
    public ResponseEntity<List<Exam>> getExams() {
        List<Exam> exams = this.examBO.findAllExams();
        if (exams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/exams/{result}")
    public ResponseEntity<List<Exam>> getExamsByResult(@PathVariable Integer result) {
        List<Exam> exams = this.examBO.findAllExams(result);
        if (exams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PutMapping("/exam/associate/{idExam}")
    public ResponseEntity<?> associateExamCourse(@PathVariable("idExam") int idExam,
                                                 @RequestBody Course provvCourse) {
        if (this.examBO.findById(idExam).isEmpty() || this.courseBO.findByCourse(provvCourse).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Exam exam = this.examBO.findById(idExam).get();
        Course course = this.courseBO.findByCourse(provvCourse).get();

        exam.setCourse(course);
        this.examBO.save(exam);

        course.getExams().add(exam);
        this.courseBO.save(course);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/exam/deassociate/{idExam}")
    public ResponseEntity<?> deassociateExamCourse(@PathVariable("idExam") int idExam) {
        if (this.examBO.findById(idExam).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Exam exam = this.examBO.findById(idExam).get();
        exam.setCourse(null);
        this.examBO.save(exam);

        for (Course course : this.courseBO.findAllCourses()) {
            course.getExams().removeIf(e -> e.equals(exam));
            this.courseBO.save(course);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idExam}")
    public ResponseEntity<?> deleteExam(@PathVariable("idExam") int idExam) {
        if (this.examBO.findById(idExam).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Exam exam = this.examBO.findById(idExam).get();
        for (Course course : this.courseBO.findAllCourses()) {
            course.getExams().removeIf(e -> e.equals(exam));
        }
        this.examBO.delete(idExam);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
