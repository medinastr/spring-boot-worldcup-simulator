package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.dto.PlayerDTO;
import com.medinastr.worldcup.entity.Player;
import com.medinastr.worldcup.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerRestController {

    private final PlayerService playerService;

    @Autowired
    public PlayerRestController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getPlayers() {
        List<PlayerDTO> players = playerService.getPlayers();
        return ResponseEntity.status(200).body(players);
    }

    @PostMapping("/savePlayer")
    public ResponseEntity<Player> savePlayer(@RequestBody PlayerDTO playerDTO) {
        Player dbPlayer = playerService.savePlayer(playerDTO);
        return ResponseEntity.status(201).body(dbPlayer);
    }

    @PostMapping("/savePlayers")
    public ResponseEntity<List<Player>> savePlayersList(@RequestBody List<PlayerDTO> playersDTO) {
        List<Player> dbPlayers = playerService.savePlayersList(playersDTO);
        return ResponseEntity.status(201).body(dbPlayers);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(RuntimeException exc) {
        return ResponseEntity.status(400).body(exc.getMessage());
    }
}
