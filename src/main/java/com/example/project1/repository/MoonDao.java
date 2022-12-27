package com.example.project1.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project1.entities.Moon;

public interface MoonDao extends JpaRepository<Moon,Integer>{
    
}
