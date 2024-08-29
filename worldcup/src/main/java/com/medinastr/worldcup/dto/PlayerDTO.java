package com.medinastr.worldcup.dto;

import com.medinastr.worldcup.entity.Player;
import jakarta.persistence.Column;

public class PlayerDTO {

    private String firstName;
    private String lastName;
    private int shirtNumber;
    private int goals;
    private int yellowCards;
    private int redCards;

    public PlayerDTO() {}

    public PlayerDTO(String firstName, String lastName, int shirtNumber, int goals, int yellowCards, int redCards) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.shirtNumber = shirtNumber;
        this.goals = goals;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
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

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public Player toPlayer() {
        Player player = new Player();
        player.setFirstName(this.getFirstName());
        player.setLastName(this.getLastName());
        player.setShirtNumber(this.getShirtNumber());
        player.setGoals(this.getGoals());
        player.setYellowCards(this.getYellowCards());
        player.setRedCards(this.getRedCards());
        return player;
    }
}
