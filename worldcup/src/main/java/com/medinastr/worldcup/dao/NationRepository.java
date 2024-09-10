package com.medinastr.worldcup.dao;

import com.medinastr.worldcup.entity.Nation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NationRepository extends JpaRepository<Nation, Integer> {

    public Optional<Nation> findByName(String name);
}
