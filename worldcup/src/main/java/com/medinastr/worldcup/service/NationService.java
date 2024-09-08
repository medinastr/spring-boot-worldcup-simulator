package com.medinastr.worldcup.service;

import com.medinastr.worldcup.dao.NationRepository;
import com.medinastr.worldcup.dto.NationDTO;
import com.medinastr.worldcup.entity.Nation;
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

    public Nation save(NationDTO nationDTO) {
        validateNationName(nationDTO.getName());
        Nation dbNation = nationDTO.toNation();
        return nationRepository.save(dbNation);
    }

    public List<Nation> saveNationsList(List<NationDTO> nationsDTO) {
        List<Nation> dbNations = nationsDTO.stream()
                .map(nationDTO -> {
                    validateNationName(nationDTO.getName());
                    return nationDTO.toNation();
                })
                .collect(Collectors.toList());
        return nationRepository.saveAll(dbNations);
    }

    public void validateNationName(String name) {
        if (name == null || name.length() < 3 || !name.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Nation name invalid.");
        }
    }


    public Optional<Nation> delete(int id) {
        Optional<Nation> findNation = nationRepository.findById(id);
        nationRepository.deleteById(id);
        return findNation;
    }
}
