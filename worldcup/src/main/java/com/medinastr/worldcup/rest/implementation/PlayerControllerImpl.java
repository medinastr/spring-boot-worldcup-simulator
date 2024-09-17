package com.medinastr.worldcup.rest.implementation;

import com.medinastr.worldcup.dto.PlayerDTO;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.entity.Player;
import com.medinastr.worldcup.rest.PlayerController;
import com.medinastr.worldcup.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerControllerImpl implements PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerControllerImpl(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public ResponseEntity<List<PlayerDTO>> getPlayers() {
        List<PlayerDTO> players = playerService.getPlayers();
        return ResponseEntity.status(200).body(players);
    }

    @Override
    public ResponseEntity<Player> savePlayer(@RequestBody PlayerDTO playerDTO) {
        Player dbPlayer = playerService.savePlayer(playerDTO);
        return ResponseEntity.status(201).body(dbPlayer);
    }

    @Override
    public ResponseEntity<List<Player>> savePlayersList(@RequestBody List<PlayerDTO> playersDTO) {
        List<Player> dbPlayers = playerService.savePlayersList(playersDTO);
        return ResponseEntity.status(201).body(dbPlayers);
    }

    @Override
    public ResponseEntity<?> deletePlayer(@PathVariable String id) {
        playerService.delete(id);
        return ResponseEntity.status(204).build();
    }
}
