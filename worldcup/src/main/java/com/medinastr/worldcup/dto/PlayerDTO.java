package com.medinastr.worldcup.dto;

import com.medinastr.worldcup.entity.Player;
import jakarta.persistence.Column;

public class PlayerDTO {

    private String firstName;
    private String lastName;
    private int shirtNumber;

    public PlayerDTO() {}

    public PlayerDTO(String firstName, String lastName, int shirtNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.shirtNumber = shirtNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public Player toPlayer() {
        Player player = new Player();
        player.setFirstName(this.getFirstName());
        player.setLastName(this.getLastName());
        player.setShirtNumber(this.getShirtNumber());
        return player;
    }
}
