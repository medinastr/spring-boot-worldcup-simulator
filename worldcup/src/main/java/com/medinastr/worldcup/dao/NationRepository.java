package com.medinastr.worldcup.dao;

import com.medinastr.worldcup.entity.Nation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationRepository extends JpaRepository<Nation, Integer> {
}
