package com.uptc.TallerSpringBoot_Posgres_UPTC.repositories;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
