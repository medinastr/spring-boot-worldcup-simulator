package com.medinastr.worldcup.entity;

import com.medinastr.worldcup.dto.PlayerDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "shirt_number")
    private int shirtNumber;

    @Column(name = "goals")
    private int goals;

    @Column(name = "yellow_cards")
    private int yellowCards;

    @Column(name = "red_cards")
    private int redCards;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "nation_id")
    private Nation nation;

    public Player() {}

    public Player(String firstName, String lastName, int shirtNumber, int goals, int yellowCards, int redCards) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.shirtNumber = shirtNumber;
        this.goals = goals;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public PlayerDTO toDTO() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setFirstName(this.getFirstName());
        playerDTO.setLastName(this.getLastName());
        playerDTO.setShirtNumber(this.getShirtNumber());
        playerDTO.setGoals(this.getGoals());
        playerDTO.setYellowCards(this.getYellowCards());
        playerDTO.setRedCards(this.getRedCards());
        return playerDTO;
    }
}
