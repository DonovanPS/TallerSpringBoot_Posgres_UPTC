package com.uptc.TallerSpringBoot_Posgres_UPTC.controller;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.School;
import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.Subject;
import com.uptc.TallerSpringBoot_Posgres_UPTC.responses.ResponseHandler;
import com.uptc.TallerSpringBoot_Posgres_UPTC.services.SchoolService;
import com.uptc.TallerSpringBoot_Posgres_UPTC.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subjects")
public class SubjectController {


    private final SubjectService subjectService;
    private final SchoolService schoolService;

    @Autowired
    public SubjectController(SubjectService subjectService, SchoolService schoolService) {
        this.subjectService = subjectService;
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllSubjects() {
        try {
            List<Subject> subjects = subjectService.getAllSubjects();
            return ResponseHandler.generateResponse("List of subjects", HttpStatus.OK, subjects);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSubjectById(Integer id) {
        try {
            Subject subject = subjectService.getSubjectById(id);
            if (subject != null) {
                return ResponseHandler.generateResponse("Subject found", HttpStatus.OK, subject);
            }
            return ResponseHandler.generateResponse("Subject not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/{idSchool}")
    public ResponseEntity<Object> saveSubject(@RequestBody Subject subject, @PathVariable Integer idSchool) {
        try {
            School school = schoolService.getSchoolById(idSchool);
            if (school != null) {
                return ResponseHandler.generateResponse("Subject saved", HttpStatus.OK, subjectService.saveSubject(subject, idSchool));
            }
            return ResponseHandler.generateResponse("School not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{schoolId}/school")
    public ResponseEntity<Object> getSchoolSubjects(@PathVariable Integer schoolId) {
        try {
            School school = schoolService.getSchoolById(schoolId);

            if (school != null) {
                List<Subject> subjects = school.getSubjects();
                return ResponseHandler.generateResponse("Subjects found", HttpStatus.OK, subjects);
            } else {
                return ResponseHandler.generateResponse("School not found", HttpStatus.NOT_FOUND, null);
            }

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSubject(@RequestBody Subject subject, @PathVariable Integer id) {
        try {
            Subject subjectToUpdate = subjectService.getSubjectById(id);
            if (subjectToUpdate != null) {
               subject.setId(id);
                return ResponseHandler.generateResponse("Subject updated", HttpStatus.OK, subjectService.saveSubject(subject, subjectToUpdate.getSchool().getId()));
            }
            return ResponseHandler.generateResponse("Subject not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSubject(@PathVariable Integer id) {
        try {
            Subject subject = subjectService.getSubjectById(id);
            if (subject != null) {
                subjectService.deleteSubject(subject);
                return ResponseHandler.generateResponse("Subject deleted", HttpStatus.OK, null);
            }
            return ResponseHandler.generateResponse("Subject not found", HttpStatus.NOT_FOUND, null);
        } catch (DataIntegrityViolationException ex) {
            // Captura la excepción de violación de integridad de datos (clave externa)
            return ResponseHandler.generateResponse("Cannot delete subject. It is still referenced by one or more students.", HttpStatus.BAD_REQUEST, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Object> getSubjectStudents(@PathVariable Integer id) {
        try {
            Subject subject = subjectService.getSubjectById(id);
            if (subject != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("subject", subject);

                return ResponseHandler.generateResponse("List of students for subject", HttpStatus.OK, response);
            }
            return ResponseHandler.generateResponse("Subject not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }



}
