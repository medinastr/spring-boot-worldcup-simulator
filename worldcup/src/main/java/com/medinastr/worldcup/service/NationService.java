package com.medinastr.worldcup.service;

import com.medinastr.worldcup.dao.NationRepository;
import com.medinastr.worldcup.dao.PlayerRepository;
import com.medinastr.worldcup.dto.NationDTO;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.entity.Player;
import com.medinastr.worldcup.exception.WorldcupConflictException;
import com.medinastr.worldcup.exception.WorldcupInvalidAttributeException;
import com.medinastr.worldcup.exception.WorldcupNotFoundException;
import com.medinastr.worldcup.mapper.MedinaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NationService {

    private final NationRepository nationRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public NationService(NationRepository nationRepository, PlayerRepository playerRepository) {
        this.nationRepository = nationRepository;
        this.playerRepository = playerRepository;
    }

    public List<NationDTO> getNations() {
        List<Nation> nations = nationRepository.findAll();
        return nations.stream()
                .map(Nation::toDTO)
                .collect(Collectors.toList());
    }

    public Nation saveNation(NationDTO nationDTO) {
        validateNation(nationDTO);
        Nation nation = MedinaMapper.parseObject(nationDTO, Nation.class);
        return nationRepository.save(nation);
    }

    public List<Nation> saveNationsList(List<NationDTO> nationsDTO) {
        List<Nation> dbNations = nationsDTO.stream()
                .map(nationDTO -> {
                    validateNation(nationDTO);
                    return nationDTO.toNation();
                })
                .collect(Collectors.toList());
        return nationRepository.saveAll(dbNations);
    }

    public void validateNation(NationDTO nationDTO) {
        if (nationDTO.getName() == null || nationDTO.getName().length() < 3
                || !nationDTO.getName().matches("[a-zA-Z\\s]+")) {
            throw new WorldcupInvalidAttributeException("Name invalid.");
        }
        if (nationRepository.findByName(nationDTO.getName()).isPresent()) {
            throw new WorldcupConflictException("Nation already exists.");
        }
        if (nationDTO.getInstitution() == null || nationDTO.getInstitution().length() < 2
                || !nationDTO.getInstitution().matches("[a-zA-Z\\s]+")) {
            throw new WorldcupInvalidAttributeException("Institution name invalid.");
        }
    }

    public void delete(String id) {
        try {
            int idAux = Integer.parseInt(id);
            Optional<Nation> dbNation = nationRepository.findById(idAux);
            if(dbNation.isEmpty()) {
                throw new WorldcupNotFoundException("Nation not exists.");
            }
            dbNation.get().getPlayers().forEach(player -> {
                player.setNation(null);
                playerRepository.save(player);
            });
            nationRepository.deleteById(idAux);
        } catch (NumberFormatException exc) {
            throw new WorldcupInvalidAttributeException("Id need to be a Integer.");
        }
    }
}
