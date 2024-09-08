package com.medinastr.worldcup.dto;

import com.medinastr.worldcup.entity.Nation;
import jakarta.persistence.Column;

public class NationDTO {

    private String name;

    public NationDTO() {}

    public NationDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nation toNation() {
        Nation nation = new Nation();
        nation.setName(this.getName());
        return nation;
    }
}
