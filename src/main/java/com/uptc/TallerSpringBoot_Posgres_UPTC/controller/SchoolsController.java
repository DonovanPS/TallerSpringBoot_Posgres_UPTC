package com.uptc.TallerSpringBoot_Posgres_UPTC.controller;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.School;
import com.uptc.TallerSpringBoot_Posgres_UPTC.responses.ResponseHandler;
import com.uptc.TallerSpringBoot_Posgres_UPTC.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolsController {


    private final SchoolService schoolService;

    @Autowired
    public SchoolsController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllSchools(){
       try {
           List<School> schools = schoolService.getAllSchools();

           return ResponseHandler.generateResponse("List of schools", HttpStatus.OK, schools);
       }catch (Exception e){
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
       }
    }



    @GetMapping("/{id}")
    public ResponseEntity<Object> getSchoolById(Integer id){
        try {
           School school = schoolService.getSchoolById(id);

           if(school != null){
               return ResponseHandler.generateResponse("School found", HttpStatus.OK, school);

           }
           return ResponseHandler.generateResponse("School not found", HttpStatus.NOT_FOUND, null);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveSchool(@RequestBody School school){
        try {
            return ResponseHandler.generateResponse("School saved", HttpStatus.OK, schoolService.saveSchool(school));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSchool(@RequestBody School school, @PathVariable Integer id){
        try {
            School updatedSchool = schoolService.getSchoolById(id);
            if(updatedSchool != null){
                school.setId(id);
                return ResponseHandler.generateResponse("School updated", HttpStatus.OK, saveSchool(school));
            }
            return ResponseHandler.generateResponse("School not found", HttpStatus.NOT_FOUND, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSchool(@PathVariable Integer id){
        try {
            School school = schoolService.getSchoolById(id);
            if(school != null){
                schoolService.deleteSchool(school);
                return ResponseHandler.generateResponse("School deleted", HttpStatus.OK, null);
            }
            return ResponseHandler.generateResponse("School not found", HttpStatus.NOT_FOUND, null);
        } catch (DataIntegrityViolationException e) {
            // Esta excepción se produce cuando hay una violación de integridad de datos, como la restricción de clave externa
            return ResponseHandler.generateResponse("Cannot delete school because it has associated subjects", HttpStatus.BAD_REQUEST, null);
        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
