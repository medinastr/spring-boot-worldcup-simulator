package com.medinastr.worldcup.service;

import com.medinastr.worldcup.dao.NationRepository;
import com.medinastr.worldcup.dao.PlayerRepository;
import com.medinastr.worldcup.dto.PlayerDTO;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final NationRepository nationRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, NationRepository nationRepository) {
        this.playerRepository = playerRepository;
        this.nationRepository = nationRepository;
    }

    public List<PlayerDTO> getPlayers() {
        List<Player> dbPlayers = playerRepository.findAll();
        List<PlayerDTO> players = dbPlayers.stream()
                .map(Player::toDTO)
                .collect(Collectors.toList());
        return players;
    }

    public Player savePlayer(PlayerDTO playerDTO) {
        Optional<Nation> nation = nationRepository.findByName(playerDTO.getNationName());
        validatePlayer(playerDTO.getFirstName(), playerDTO.getLastName(),
                playerDTO.getShirtNumber(), nation);
        Player player = playerDTO.toPlayer();
        player.setNation(nation.get());
        return playerRepository.save(player);
    }

    public List<Player> savePlayersList(List<PlayerDTO> playersDTO) {
        List<Player> dbPlayers = playersDTO.stream()
                .map(playerDTO -> {
                    Optional<Nation> nation = nationRepository.findByName(playerDTO.getNationName());
                    validatePlayer(playerDTO.getFirstName(), playerDTO.getLastName(),
                            playerDTO.getShirtNumber(), nation);
                    Player player = playerDTO.toPlayer();
                    player.setNation(nation.get());
                    return player;
                })
                .collect(Collectors.toList());
        return playerRepository.saveAll(dbPlayers);
    }

    public void validatePlayer(String firstName, String lastName, Integer shirtNumber, Optional<Nation> nation) {
        if (firstName == null || firstName.length() < 2) {
            throw new RuntimeException("First name invalid.");
        } else if (lastName == null || lastName.length() < 2) {
            throw new RuntimeException("Last name invalid.");
        } else if (shirtNumber == null || shirtNumber < 1 || shirtNumber > 26) {
            throw new RuntimeException("Shirt number must be between 1 and 26.");
        } else if (nation.isEmpty()) {
            throw new RuntimeException("Invalid nation name.");
        } else if (nationRepository.findByName(nation.get().getName())
                .get().getPlayers().size() >= 26) {
            throw new RuntimeException("There are already 26 players in the nation.");
        }
    }

}
