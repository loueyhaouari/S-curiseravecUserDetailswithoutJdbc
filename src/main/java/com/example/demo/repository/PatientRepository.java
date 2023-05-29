package com.example.demo.repository;

import com.example.demo.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    //Pageable sert à la pagination
    Page<Patient>findByNomContains(String keyword, Pageable pageable);
  //Lorque une méthode retourne de type page elle doit avoir un paramètre de type pageable
   @Query("select p from Patient p where p.nom like :x")
    Page<Patient>chercher(@Param("x") String keyword,Pageable pageable);

}
