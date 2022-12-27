package com.example.project1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project1.entities.Planet;

public interface PlanetDao extends JpaRepository<Planet,Integer>{
    Optional<Planet> findByName(String name);
}
