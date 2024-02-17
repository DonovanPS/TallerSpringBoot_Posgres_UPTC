package com.uptc.TallerSpringBoot_Posgres_UPTC.services;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.School;
import com.uptc.TallerSpringBoot_Posgres_UPTC.repositories.SchoolsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolsService {


    private final SchoolsRepository schoolsRepository;

    @Autowired
    public SchoolsService(SchoolsRepository schoolsRepository) {
        this.schoolsRepository = schoolsRepository;
    }

    public List<School> getAllSchools(){
        return schoolsRepository.findAll();
    }


    public School getSchoolById(Integer id){
        Optional<School> optional = schoolsRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public School saveSchool(School school){
        return schoolsRepository.save(school);
    }



}
