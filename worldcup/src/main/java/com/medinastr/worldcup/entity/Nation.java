package com.medinastr.worldcup.entity;

import com.medinastr.worldcup.dto.NationDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="nation")
public class Nation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToOne(mappedBy = "nation")
    private Institution institution;

    @OneToMany(mappedBy = "nation",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Player> players;

    public Nation() {}

    public Nation(String name) {
        this.name = name;
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

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public NationDTO toDTO() {
        NationDTO nationDTO = new NationDTO();
        nationDTO.setName(this.getName());
        return nationDTO;
    }

    @Override
    public String toString() {
        return "Nation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", institution=" + institution +
                ", players=" + players +
                '}';
    }
}
