package com.uptc.TallerSpringBoot_Posgres_UPTC.controller;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.School;
import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.SchoolDirector;
import com.uptc.TallerSpringBoot_Posgres_UPTC.responses.ResponseHandler;
import com.uptc.TallerSpringBoot_Posgres_UPTC.services.SchoolDirectorService;
import com.uptc.TallerSpringBoot_Posgres_UPTC.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school-directors")
public class SchoolDirectorController {

    private final SchoolDirectorService schoolDirectorService;
    private final SchoolService schoolService;

    @Autowired
    public SchoolDirectorController(SchoolDirectorService schoolDirectorService, SchoolService schoolService) {
        this.schoolDirectorService = schoolDirectorService;
        this.schoolService = schoolService;
    }

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

    @PostMapping("/{idSchool}")
    public ResponseEntity<Object> saveSchoolDirector(@RequestBody SchoolDirector schoolDirector, @PathVariable Integer idSchool) {
        try {
            School school = schoolService.getSchoolById(idSchool);

            if (school != null) {
                // Verificar si la escuela ya tiene un director asignado
                if (school.getSchoolDirector() != null) {
                    return ResponseHandler.generateResponse("School already has a director assigned", HttpStatus.BAD_REQUEST, null);
                }

                // Asignar el director a la escuela
                schoolDirector.setSchool(school);
                school.setSchoolDirector(schoolDirector);

                return ResponseHandler.generateResponse("School director saved", HttpStatus.OK, schoolDirectorService.saveSchoolDirector(schoolDirector));
            }

            return ResponseHandler.generateResponse("School not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSchoolDirector(@RequestBody SchoolDirector schoolDirector, @PathVariable Integer id) {
        try {
            SchoolDirector updatedSchoolDirector = schoolDirectorService.getSchoolDirectorById(id);
            if (updatedSchoolDirector != null) {
                schoolDirector.setId(id);
                return ResponseHandler.generateResponse("School director updated", HttpStatus.OK, schoolDirectorService.saveSchoolDirector(schoolDirector));
            }
            return ResponseHandler.generateResponse("School director not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSchoolDirector(@PathVariable Integer id) {
        try {
            SchoolDirector schoolDirector = schoolDirectorService.getSchoolDirectorById(id);
            if (schoolDirector != null) {
                schoolDirectorService.deleteSchoolDirector(id);
                return ResponseHandler.generateResponse("School director deleted", HttpStatus.OK, null);
            }
            return ResponseHandler.generateResponse("School director not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
