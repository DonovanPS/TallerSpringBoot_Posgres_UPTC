package com.uptc.TallerSpringBoot_Posgres_UPTC.repositories;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
