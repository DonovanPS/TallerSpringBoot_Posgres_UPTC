package com.uptc.TallerSpringBoot_Posgres_UPTC.controller;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.School;
import com.uptc.TallerSpringBoot_Posgres_UPTC.responses.ResponseHandler;
import com.uptc.TallerSpringBoot_Posgres_UPTC.services.SchoolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolsController {


    private final SchoolsService schoolsService;

    @Autowired
    public SchoolsController(SchoolsService schoolsService) {
        this.schoolsService = schoolsService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllSchools(){
       try {
           List<School> schools = schoolsService.getAllSchools();

           return ResponseHandler.generateResponse("List of schools", HttpStatus.OK, schools);
       }catch (Exception e){
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
       }
    }



    @GetMapping("/{id}")
    public ResponseEntity<Object> getSchoolById(Integer id){
        try {
           School school = schoolsService.getSchoolById(id);

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
            return ResponseHandler.generateResponse("School saved", HttpStatus.OK, schoolsService.saveSchool(school));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
