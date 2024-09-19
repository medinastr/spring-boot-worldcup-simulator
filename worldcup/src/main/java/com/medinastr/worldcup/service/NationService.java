package com.medinastr.worldcup.service;

import com.medinastr.worldcup.dao.NationRepository;
import com.medinastr.worldcup.dao.PlayerRepository;
import com.medinastr.worldcup.dto.NationDTO;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.exception.WorldcupConflictException;
import com.medinastr.worldcup.exception.WorldcupInvalidAttributeException;
import com.medinastr.worldcup.exception.WorldcupNotFoundException;
import com.medinastr.worldcup.mapper.MedinaMapper;
import com.medinastr.worldcup.rest.NationController;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

    public List<NationDTO> getNationsList() {
        List<Nation> nations = nationRepository.findAll();
        return nations.stream()
                .map(nation -> {
                    NationDTO nationDTO = nation.toDTO();
                    String id = Integer.toString(nation.getId());
                    nationDTO.add(linkTo(methodOn(NationController.class).getById(id)).withSelfRel());
                    return nationDTO;
                })
                .collect(Collectors.toList());
    }

    public NationDTO getNation(String id) {
        try {
            int idAux = Integer.parseInt(id);
            Optional<Nation> dbNation = nationRepository.findById(idAux);
            if(dbNation.isEmpty()) {
                throw new WorldcupNotFoundException("Nation not found.");
            }
            NationDTO nationDTO = dbNation.get().toDTO();
            nationDTO.add(linkTo(methodOn(NationController.class).getById(id)).withSelfRel()); // um endereço para ele mesmo
            return nationDTO;
        } catch (NumberFormatException exc) {
            throw new WorldcupInvalidAttributeException("Id need to be a Integer.");
        }
    }

    public NationDTO saveNation(NationDTO nationDTO) {
        validateNation(nationDTO);
        Nation nation = nationRepository.save(nationDTO.toNation());
        String id = Integer.toString(nation.getId());
        nationDTO.add(linkTo(methodOn(NationController.class).getById(id)).withSelfRel());
        return nationDTO;
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
