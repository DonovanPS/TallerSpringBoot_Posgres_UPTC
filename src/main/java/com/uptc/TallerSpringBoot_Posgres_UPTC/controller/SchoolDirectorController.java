package com.uptc.TallerSpringBoot_Posgres_UPTC.controller;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.SchoolDirector;
import com.uptc.TallerSpringBoot_Posgres_UPTC.responses.ResponseHandler;
import com.uptc.TallerSpringBoot_Posgres_UPTC.services.SchoolDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school-directors")
public class SchoolDirectorController {

    private final SchoolDirectorService schoolDirectorService;

    public SchoolDirectorController(SchoolDirectorService schoolDirectorService) {
        this.schoolDirectorService = schoolDirectorService;
    }

    @Autowired


    @GetMapping
    public ResponseEntity<Object> getAllSchoolDirectors() {
        try {
            List<SchoolDirector> schoolDirectors = schoolDirectorService.getAllSchoolDirectors();

            return ResponseHandler.generateResponse("List of school directors", HttpStatus.OK, schoolDirectors);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSchoolDirectorById(Integer id) {
        try {
            SchoolDirector schoolDirector = schoolDirectorService.getSchoolDirectorById(id);

            if (schoolDirector != null) {
                return ResponseHandler.generateResponse("School director found", HttpStatus.OK, schoolDirector);

            }
            return ResponseHandler.generateResponse("School director not found", HttpStatus.NOT_FOUND, null);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveSchoolDirector(@RequestBody SchoolDirector schoolDirector) {
        try {
            return ResponseHandler.generateResponse("School director saved", HttpStatus.OK, schoolDirectorService.saveSchoolDirector(schoolDirector));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
