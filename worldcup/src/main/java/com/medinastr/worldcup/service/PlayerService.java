package com.medinastr.worldcup.service;

import com.medinastr.worldcup.dao.NationRepository;
import com.medinastr.worldcup.dao.PlayerRepository;
import com.medinastr.worldcup.dto.PlayerDTO;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.entity.Player;
import com.medinastr.worldcup.exception.WorldcupConflictException;
import com.medinastr.worldcup.exception.WorldcupInvalidAttributeException;
import com.medinastr.worldcup.exception.WorldcupNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .map(player -> {
                    PlayerDTO playerDTO = player.toDTO();
                    if(player.getNation() != null) {
                        playerDTO.setNationName(player.getNation().getName());
                    }
                    return playerDTO;
                })
                .collect(Collectors.toList());
        return players;
    }

    @Transactional
    public PlayerDTO savePlayer(PlayerDTO playerDTO) {
        Optional<Nation> nation = nationRepository.findByName(playerDTO.getNationName());
        validatePlayer(playerDTO.getFirstName(), playerDTO.getLastName(),
                playerDTO.getShirtNumber(), nation);
        Player player = playerDTO.toPlayer();
        player.setNation(nation.get());
        playerRepository.save(player);
        return playerDTO;
    }

    @Transactional
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
            throw new WorldcupInvalidAttributeException("First name invalid.");
        } else if (lastName == null || lastName.length() < 2) {
            throw new WorldcupInvalidAttributeException("Last name invalid.");
        } else if (shirtNumber == null || shirtNumber < 1 || shirtNumber > 26) {
            throw new WorldcupInvalidAttributeException("Shirt number must be between 1 and 26.");
        } else if (nation.isEmpty()) {
            throw new WorldcupInvalidAttributeException("Invalid nation name.");
        } else if (nationRepository.findByName(nation.get().getName())
                .get().getPlayers().size() >= 26) {
            throw new WorldcupConflictException("There are already 26 players in the nation.");
        }
    }

    public void delete(String id) {
        try {
            int idAux = Integer.parseInt(id);
            Optional<Player> dbPlayer = playerRepository.findById(idAux);
            if(dbPlayer.isEmpty()) {
                throw new WorldcupNotFoundException("Player not exists.");
            }
            Player player = dbPlayer.get();
            player.setNation(null);
            playerRepository.save(player);
            playerRepository.deleteById(idAux);
        } catch (NumberFormatException exc) {
            throw new WorldcupInvalidAttributeException("Id need to be a Integer.");
        }
    }
}
