package com.example.project1.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

@Entity
@Table(name="moons")
public class Moon {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="name")
	private String name;
    @Column(name="myplanetid")
	private int myPlanetId;
    @ManyToOne
    private Planet planet;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMyPlanetId() {
        return myPlanetId;
    }
    public void setMyPlanetId(int myPlanetId) {
        this.myPlanetId = myPlanetId;
    }
    @Override
    public String toString() {
        return "Moon [id=" + id + ", name=" + name + ", myPlanetId=" + myPlanetId + "]";
    }
}
