package com.medinastr.worldcup.service;

import com.medinastr.worldcup.dao.NationRepository;
import com.medinastr.worldcup.dto.NationDTO;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.exception.WorldcupConflictException;
import com.medinastr.worldcup.exception.WorldcupInvalidAttributeException;
import com.medinastr.worldcup.exception.WorldcupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NationService {

    private final NationRepository nationRepository;

    @Autowired
    public NationService(NationRepository nationRepository) {
        this.nationRepository = nationRepository;
    }

    public List<NationDTO> getNations() {
        List<Nation> nations = nationRepository.findAll();
        return nations.stream()
                .map(Nation::toDTO)
                .collect(Collectors.toList());
    }

    public Nation saveNation(NationDTO nationDTO) {
        validateNation(nationDTO);
        return nationRepository.save(nationDTO.toNation());
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

    public Optional<Nation> delete(int id) {
        Optional<Nation> dbNation = nationRepository.findById(id);
        if(dbNation.isEmpty()) {
            throw new WorldcupNotFoundException("Nation not exists.");
        }
        nationRepository.deleteById(id);
        return dbNation;
    }
}
