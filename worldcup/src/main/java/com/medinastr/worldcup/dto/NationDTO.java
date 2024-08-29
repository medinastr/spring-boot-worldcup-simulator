package com.medinastr.worldcup.dto;

import com.medinastr.worldcup.entity.Nation;
import jakarta.persistence.Column;

public class NationDTO {

    private String name;
    private int wins;
    private int goals;
    private int goalsConceded;

    public NationDTO() {}

    public NationDTO(String name, int wins, int goals, int goalsConceded) {
        this.name = name;
        this.wins = wins;
        this.goals = goals;
        this.goalsConceded = goalsConceded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
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

    public Nation toNation() {
        Nation nation = new Nation();
        nation.setName(this.getName());
        nation.setWins(this.getWins());
        nation.setGoals(this.getGoals());
        nation.setGoalsConceded(this.getGoalsConceded());
        return nation;
    }
}
