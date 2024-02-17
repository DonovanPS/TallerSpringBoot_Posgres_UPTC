package com.uptc.TallerSpringBoot_Posgres_UPTC.repositories;

import com.uptc.TallerSpringBoot_Posgres_UPTC.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolsRepository extends JpaRepository<School, Integer> {

}
