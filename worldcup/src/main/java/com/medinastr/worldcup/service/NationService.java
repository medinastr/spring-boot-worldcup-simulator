package com.medinastr.worldcup.service;

import com.medinastr.worldcup.dao.NationRepository;
import com.medinastr.worldcup.dto.NationDTO;
import com.medinastr.worldcup.entity.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NationService {

    private NationRepository nationRepository;

    @Autowired
    public NationService(NationRepository nationRepository) {
        this.nationRepository = nationRepository;
    }

    public List<NationDTO> getNations() {
        List<Nation> nations = nationRepository.findAll();
        return nations.stream()
                .map(nation -> nation.toDTO())
                .collect(Collectors.toList());
    }

    public Nation save(NationDTO nationDTO) {
        Nation dbNation = nationDTO.toNation();
        return nationRepository.save(dbNation);
    }

    public Optional<Nation> delete(int id) {
        Optional<Nation> findNation = nationRepository.findById(id);
        nationRepository.deleteById(id);
        return findNation;
    }
}
