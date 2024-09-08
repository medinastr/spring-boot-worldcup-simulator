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

    @PostMapping("/save")
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerDTO playerDTO) {
        Player dbPlayer = playerService.addPlayer(playerDTO);
        return ResponseEntity.status(201).body(dbPlayer);
    }
}
