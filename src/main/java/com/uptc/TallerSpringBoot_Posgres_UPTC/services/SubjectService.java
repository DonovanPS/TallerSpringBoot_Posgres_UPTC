package com.uptc.TallerSpringBoot_Posgres_UPTC.services;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.School;
import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.Subject;
import com.uptc.TallerSpringBoot_Posgres_UPTC.repositories.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {


    private final SubjectRepository subjectRepository;
    private final SchoolService schoolService;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, SchoolService schoolService) {
        this.subjectRepository = subjectRepository;
        this.schoolService = schoolService;
    }
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Integer id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public Subject saveSubject(Subject subject, Integer idSchool) {
        if (idSchool != null) {
            School school = schoolService.getSchoolById(idSchool);
            if (school != null) {
                subject.setSchool(school);
            } else {
                throw new EntityNotFoundException("School not found with id: " + idSchool);
            }
        }
        return subjectRepository.save(subject);
    }


    public void deleteSubject(Subject subject) {
        subjectRepository.delete(subject);
    }
}
