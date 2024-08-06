package com.medinastr.worldcup.entity;

import jakarta.persistence.*;

@Entity
@Table(name="nation")
public class Nation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nation_name")
    private String nationName;

    @Column(name="goals")
    private int goals;

    @Column(name="goals_conceded")
    private int goalsConceded;

    public Nation() {}

    public Nation(String nationName, int goals, int goalsConceded) {
        this.nationName = nationName;
        this.goals = goals;
        this.goalsConceded = goalsConceded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    @Override
    public String toString() {
        return "Nation{" +
                "id=" + id +
                ", nationName='" + nationName + '\'' +
                ", goals=" + goals +
                ", goalsConceded=" + goalsConceded +
                '}';
    }
}
