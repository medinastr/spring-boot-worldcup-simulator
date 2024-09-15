package com.medinastr.worldcup.dto;

import com.medinastr.worldcup.entity.Stadium;

public class StadiumDTO {

    private String name;
    private int capacity;
    private String city;
    private double gameRental;

    public StadiumDTO() {}

    public StadiumDTO(String name, int capacity, String city, double gameRental) {
        this.name = name;
        this.capacity = capacity;
        this.city = city;
        this.gameRental = gameRental;
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

    public void setGameRental(double gameRental) {
        this.gameRental = gameRental;
    }

    public Stadium toStadium() {
        Stadium stadium = new Stadium();
        stadium.setName(this.getName());
        stadium.setCapacity(this.getCapacity());
        stadium.setCity(this.getCity());
        stadium.setGameRental(this.getGameRental());
        return stadium;
    }
}
