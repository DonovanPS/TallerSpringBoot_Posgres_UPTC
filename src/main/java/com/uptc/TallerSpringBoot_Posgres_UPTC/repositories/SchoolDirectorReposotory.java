package com.uptc.TallerSpringBoot_Posgres_UPTC.repositories;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.SchoolDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolDirectorReposotory  extends JpaRepository<SchoolDirector, Integer> {
}
