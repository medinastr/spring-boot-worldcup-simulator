package com.medinastr.worldcup.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stadium")
public class Stadium {

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

    @Column(name = "address")
    private String address;

    @Column(name = "game_rental")
    private int gameRental;

    public Stadium() {}

    public Stadium(String name, int capacity, String city, String address, int gameRental) {
        this.name = name;
        this.capacity = capacity;
        this.city = city;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGameRental() {
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
                ", address='" + address + '\'' +
                ", gameRental=" + gameRental +
                '}';
    }
}
