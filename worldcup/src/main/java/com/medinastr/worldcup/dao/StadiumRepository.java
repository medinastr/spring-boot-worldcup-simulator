package com.medinastr.worldcup.dao;

import com.medinastr.worldcup.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StadiumRepository extends JpaRepository<Stadium, Integer> {

    public Optional<Stadium> findByName(String name);
}
