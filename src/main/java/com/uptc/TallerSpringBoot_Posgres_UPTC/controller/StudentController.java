package com.uptc.TallerSpringBoot_Posgres_UPTC.controller;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.Student;
import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.Subject;
import com.uptc.TallerSpringBoot_Posgres_UPTC.responses.ResponseHandler;
import com.uptc.TallerSpringBoot_Posgres_UPTC.services.StudentService;
import com.uptc.TallerSpringBoot_Posgres_UPTC.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public StudentController(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            return ResponseHandler.generateResponse("List of students", HttpStatus.OK, students);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(Integer id) {
        try {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                return ResponseHandler.generateResponse("Student found", HttpStatus.OK, student);
            }
            return ResponseHandler.generateResponse("Student not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/{idSubject}")
    public ResponseEntity<Object> saveStudent(@RequestBody Student student, @PathVariable Integer idSubject) {
        try {
            Subject subject = subjectService.getSubjectById(idSubject);

            if (subject != null) {
                return ResponseHandler.generateResponse("Student saved", HttpStatus.OK, studentService.saveStudent(student));
            }
            return ResponseHandler.generateResponse("Subject not found", HttpStatus.NOT_FOUND, null);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


}
