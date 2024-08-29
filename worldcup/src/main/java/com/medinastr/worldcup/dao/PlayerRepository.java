package com.medinastr.worldcup.dao;

import com.medinastr.worldcup.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
