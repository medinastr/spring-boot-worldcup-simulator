package com.medinastr.worldcup.service;

import com.medinastr.worldcup.dao.StadiumRepository;
import com.medinastr.worldcup.entity.Stadium;
import com.medinastr.worldcup.exception.WorldcupConflictException;
import com.medinastr.worldcup.exception.WorldcupInvalidAttributeException;
import com.medinastr.worldcup.exception.WorldcupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    @Autowired
    public StadiumService(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    // for GET -> /stadiums
    public List<Stadium> getStadiumsList() {
        return stadiumRepository.findAll();
    }

    // for GET -> /stadium?name="****"
    public Stadium getStadium(String name) {
        String dbName = name.replace("+", " ");
        Optional<Stadium> stadium = stadiumRepository.findByName(dbName);
        if(!name.matches("[a-zA-Z\\s]+")) {
            throw new WorldcupInvalidAttributeException("Stadium name invalid.");
        } else if (stadium.isEmpty()) {
            throw new WorldcupNotFoundException("Stadium not found.");
        }
        return stadium.get();
    }

    // for POST -> /stadiums/save
    public Stadium saveStadium(Stadium stadium) {
        validateStadium(stadium);
        return stadiumRepository.save(stadium);
    }

    // for POST -> /stadiums/saveAll
    public List<Stadium> saveStadiumsList(List<Stadium> stadiums) {
        return stadiums.stream()
                .map(stadium -> {
                    validateStadium(stadium);
                    return stadiumRepository.save(stadium);
                })
                .collect(Collectors.toList());
    }

    public void validateStadium(Stadium stadium) {
        if (stadiumRepository.findByName(stadium.getName()).isPresent()) {
            throw new WorldcupConflictException("Stadium already exists: " + stadium.getName());
        } else if(stadium.getCapacity() < 30000) {
            throw new WorldcupInvalidAttributeException("Capacity must be more than 30000.");
        } else if(stadium.getName() == null || stadium.getName().length() < 2) {
            throw new WorldcupInvalidAttributeException("Stadium name invalid: " + stadium.getName());
        } else if(stadium.getCity() == null || stadium.getCity().length() < 2
                || !stadium.getCity().matches("[a-zA-Z\\s]+")) {
            throw new WorldcupInvalidAttributeException("Stadium city invalid: " + stadium.getCity());
        } else if(stadium.getGameRental() < 0) {
            throw new WorldcupInvalidAttributeException("Game rental must be more that 0.");
        }
    }
}
