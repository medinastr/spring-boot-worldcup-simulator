package com.medinastr.worldcup.entity;

import com.medinastr.worldcup.dto.NationDTO;
import jakarta.persistence.*;

@Entity
@Table(name="nation")
public class Nation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="wins")
    private int wins;

    @Column(name="goals")
    private int goals;

    @Column(name="goals_conceded")
    private int goalsConceded;

    @OneToOne(mappedBy = "nation")
    private Institution institution;

    public Nation() {}

    public Nation(String name, int wins, int goals, int goalsConceded) {
        this.name = name;
        this.wins = wins;
        this.goals = goals;
        this.goalsConceded = goalsConceded;
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

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public NationDTO toDTO() {
        NationDTO nationDTO = new NationDTO();
        nationDTO.setName(this.getName());
        nationDTO.setWins(this.getWins());
        nationDTO.setGoals(this.getGoals());
        nationDTO.setGoalsConceded(this.getGoalsConceded());
        return nationDTO;
    }

    @Override
    public String toString() {
        return "Nation{" +
                "id=" + id +
                ", nationName='" + name + '\'' +
                ", wins=" + wins +
                ", goals=" + goals +
                ", goalsConceded=" + goalsConceded +
                '}';
    }
}
