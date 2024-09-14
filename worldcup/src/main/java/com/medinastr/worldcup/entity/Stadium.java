package com.medinastr.worldcup.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "stadium")
public class Stadium implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "city")
    private String city;

    @Column(name = "game_rental")
    private double gameRental;

    public Stadium() {}

    public Stadium(String name, int capacity, String city, double gameRental) {
        this.name = name;
        this.capacity = capacity;
        this.city = city;
        this.gameRental = gameRental;
    }

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getGameRental() {
        return gameRental;
    }

    public void setGameRental(int gameRental) {
        this.gameRental = gameRental;
    }

    @Override
    public String toString() {
        return "Stadium{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", city='" + city + '\'' +
                ", gameRental=" + gameRental +
                '}';
    }
}
