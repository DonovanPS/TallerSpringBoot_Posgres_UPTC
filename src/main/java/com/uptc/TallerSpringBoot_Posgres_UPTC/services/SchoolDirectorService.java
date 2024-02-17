package com.uptc.TallerSpringBoot_Posgres_UPTC.services;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.SchoolDirector;
import com.uptc.TallerSpringBoot_Posgres_UPTC.repositories.SchoolDirectorReposotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SchoolDirectorService {

    private final SchoolDirectorReposotory schoolDirectorReposotory;

    @Autowired
    public SchoolDirectorService(SchoolDirectorReposotory schoolDirectorReposotory) {
        this.schoolDirectorReposotory = schoolDirectorReposotory;
    }

    public List<SchoolDirector> getAllSchoolDirectors(){
        return schoolDirectorReposotory.findAll();
    }

    public SchoolDirector getSchoolDirectorById(Integer id){
        return schoolDirectorReposotory.findById(id).orElse(null);
    }

    public SchoolDirector saveSchoolDirector(SchoolDirector schoolDirector){
        return schoolDirectorReposotory.save(schoolDirector);
    }
}
