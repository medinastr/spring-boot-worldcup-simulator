package com.medinastr.worldcup.service;

import com.medinastr.worldcup.dao.PlayerRepository;
import com.medinastr.worldcup.dto.PlayerDTO;
import com.medinastr.worldcup.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerDTO> getPlayers() {
        List<Player> dbPlayers = playerRepository.findAll();
        List<PlayerDTO> players = dbPlayers.stream()
                .map(Player::toDTO)
                .collect(Collectors.toList());
        return players;
    }

    public Player addPlayer(PlayerDTO playerDTO) {
        Player player = playerDTO.toPlayer();
        return playerRepository.save(player);
    }
}
