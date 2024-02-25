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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Object> getStudentById(@PathVariable Integer id) {
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

    @GetMapping("/{id}/subjects/school")
    public ResponseEntity<Object> getStudentWithSubjectsAndSchool(@PathVariable Integer id) {
        try {
            Student student = studentService.getStudentById(id);

            if (student != null) {
                List<Subject> subjects = student.getSubjects();

                // Construimos la respuesta
                Map<String, Object> response = new HashMap<>();
                response.put("student", student);
                response.put("subjects", subjects); // Devolvemos las materias como están, ya que ya tienen la información de la escuela

                return ResponseHandler.generateResponse("Student with subjects and school found", HttpStatus.OK, response);
            }

            return ResponseHandler.generateResponse("Student not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }



    @PostMapping()
    public ResponseEntity<Object> saveStudent(@RequestBody Student student) {
        try {


                Student savedStudent = studentService.saveStudent(student, null);

                return ResponseHandler.generateResponse("Student saved", HttpStatus.OK, savedStudent);


        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/{idStudent}/subjects/{idSubject}")
    public ResponseEntity<Object> addSubjectToStudent(@PathVariable Integer idStudent, @PathVariable Integer idSubject) {
        try {
            // Obtener el estudiante y la materia por sus respectivos IDs
            Student student = studentService.getStudentById(idStudent);
            Subject subject = subjectService.getSubjectById(idSubject);

            // Verificar si el estudiante y la materia existen
            if (student != null && subject != null) {
                // Verificar si el estudiante ya tiene la materia inscrita
                if (student.getSubjects().contains(subject)) {
                    return ResponseHandler.generateResponse("Student already enrolled in this subject", HttpStatus.BAD_REQUEST, null);
                }

                // Obtener las materias actuales del estudiante
                List<Subject> studentSubjects = student.getSubjects();

                // Agregar la nueva materia a la lista
                studentSubjects.add(subject);

                // Guardar el estudiante actualizado
                studentService.saveStudent(student, studentSubjects);

                return ResponseHandler.generateResponse("Subject added to student successfully", HttpStatus.OK, null);
            } else {
                // Devolver un mensaje de error si el estudiante o la materia no existen
                return ResponseHandler.generateResponse("Student or subject not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            // Manejar cualquier excepción y devolver un mensaje de error
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{idStudent}/subjects/{idSubject}")
    public ResponseEntity<Object> removeSubjectFromStudent(@PathVariable Integer idStudent, @PathVariable Integer idSubject) {
        try {
            // Obtener el estudiante y la materia por sus respectivos IDs
            Student student = studentService.getStudentById(idStudent);
            Subject subject = subjectService.getSubjectById(idSubject);

            // Verificar si el estudiante y la materia existen
            if (student != null && subject != null) {
                // Verificar si el estudiante ya tiene la materia inscrita
                if (!student.getSubjects().contains(subject)) {
                    return ResponseHandler.generateResponse("Student is not enrolled in this subject", HttpStatus.BAD_REQUEST, null);
                }

                // Obtener las materias actuales del estudiante
                List<Subject> studentSubjects = student.getSubjects();

                // Remover la materia de la lista
                studentSubjects.remove(subject);

                // Guardar el estudiante actualizado
                studentService.saveStudent(student, studentSubjects);

                return ResponseHandler.generateResponse("Subject removed from student successfully", HttpStatus.OK, null);
            } else {
                return ResponseHandler.generateResponse("Student or subject not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        try {
            Student studentToUpdate = studentService.getStudentById(id);
            if (studentToUpdate != null) {
                student.setId(id);

                return ResponseHandler.generateResponse("Student updated", HttpStatus.OK, studentService.saveStudent(student, studentToUpdate.getSubjects()));
            }
            return ResponseHandler.generateResponse("Student not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Integer id) {
        try {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                studentService.deleteStudent(id);
                return ResponseHandler.generateResponse("Student deleted", HttpStatus.OK, null);
            }
            return ResponseHandler.generateResponse("Student not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}


