package com.example.project1.repository;

//import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

//import com.example.project1.entities.Moon;
import com.example.project1.entities.Planet;

public interface PlanetDao extends JpaRepository<Planet,Integer>{
    Optional<Planet> findByName(String name);
    //List<Moon> findMoonsById(int id);

    @Transactional
    @Modifying
    @Query(value = "insert into planets values (default, :name, :ownerid)", nativeQuery = true)
    void createPlanet(@Param("name") String name, @Param("ownerid")  int ownerid);

    // @Transactional
    // @Modifying
    // @Query(value = "update planets set name = :name , ownerid = :ownerid where id = :id", nativeQuery = true)
    // int updatePlanet(@Param("name") String name, @Param("ownerid")  int ownerid, @Param("id")  int id);  
}
