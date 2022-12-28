package com.example.project1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import com.example.project1.entities.Moon;
import com.example.project1.exceptions.EntityNotFound;
import com.example.project1.repository.MoonDao;

@Service
public class MoonService {
    @Autowired
    private MoonDao moonDao;
    
    public String createMoon(Moon moon){
        this.moonDao.createMoon(moon.getName(), moon.getMyPlanetId());
        return "Moon Created!";
    }

    // public String updateMoon(Moon moon){
    //     int rowCount = this.moonDao.updateMoon(moon.getName(), moon.getOwnerId(), moon.getId());
    //     if (rowCount == 1){
    //         return "Moon Updated Successfully";
    //     } else {
    //         return "Could not update table";
    //     }
    // }

    public String deleteMoonById(int id){
        this.moonDao.deleteById(id);
        return "Moon with given ID deleted";
    }

    public Moon getMoonById(int id){
        Optional<Moon> possibleMoon = this.moonDao.findById(id);
        if (possibleMoon.isPresent()){
            return possibleMoon.get();
        } else {
            throw new EntityNotFound("Moon Not Found");
        }
    }

    public Moon getMoonByName(String name){
        Optional<Moon> possibleMoon = this.moonDao.findByName(name);
        if (possibleMoon.isPresent()){
            return possibleMoon.get();
        } else {
            throw new EntityNotFound("Moon Not Found");
        }
    }

    public List<Moon> getAllMoons() {
        return this.moonDao.findAll();
    }
    
    public List<Moon> getMoonsByMoonId(Iterable<Integer> id) {
         return this.moonDao.findAllById(id);
    }
}
