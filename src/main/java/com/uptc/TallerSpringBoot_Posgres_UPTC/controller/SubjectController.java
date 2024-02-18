package com.uptc.TallerSpringBoot_Posgres_UPTC.controller;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.School;
import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.Subject;
import com.uptc.TallerSpringBoot_Posgres_UPTC.responses.ResponseHandler;
import com.uptc.TallerSpringBoot_Posgres_UPTC.services.SchoolsService;
import com.uptc.TallerSpringBoot_Posgres_UPTC.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {


    private final SubjectService subjectService;
    private final SchoolsService schoolService;

    @Autowired
    public SubjectController(SubjectService subjectService, SchoolsService schoolService) {
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
                return ResponseHandler.generateResponse("Subject saved", HttpStatus.OK, subjectService.saveSubject(subject));
            }
            return ResponseHandler.generateResponse("School not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


}
