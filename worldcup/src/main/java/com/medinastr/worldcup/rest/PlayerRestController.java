package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.dto.PlayerDTO;
import com.medinastr.worldcup.entity.Player;
import com.medinastr.worldcup.exception.WorldcupConflictException;
import com.medinastr.worldcup.exception.WorldcupInvalidAttributeException;
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

    @PostMapping("/save")
    public ResponseEntity<Player> savePlayer(@RequestBody PlayerDTO playerDTO) {
        Player dbPlayer = playerService.savePlayer(playerDTO);
        return ResponseEntity.status(201).body(dbPlayer);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<Player>> savePlayersList(@RequestBody List<PlayerDTO> playersDTO) {
        List<Player> dbPlayers = playerService.savePlayersList(playersDTO);
        return ResponseEntity.status(201).body(dbPlayers);
    }

    @ExceptionHandler
    public ResponseEntity<String> invalidAttribute(WorldcupInvalidAttributeException exc) {
        return ResponseEntity.status(exc.getStatus()).body(exc.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> conflict(WorldcupConflictException exc) {
        return ResponseEntity.status(exc.getStatus()).body(exc.getMessage());
    }
}
