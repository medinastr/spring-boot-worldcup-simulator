package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.entity.Stadium;
import com.medinastr.worldcup.exception.WorldcupConflictException;
import com.medinastr.worldcup.exception.WorldcupInvalidAttributeException;
import com.medinastr.worldcup.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stadiums")
public class StadiumRestController {

    private final StadiumService stadiumService;

    @Autowired
    public StadiumRestController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @GetMapping
    public ResponseEntity<List<Stadium>> getStadiumsList() {
        List<Stadium> stadiums = stadiumService.getStadiumsList();
        return ResponseEntity.status(200).body(stadiums);
    }

    @GetMapping("/find")
    public ResponseEntity<Stadium> getStadium(@RequestParam String name) {
        Stadium stadium = stadiumService.getStadium(name);
        return ResponseEntity.status(200).body(stadium);
    }

    @PostMapping("/saveAll")
    ResponseEntity<List<Stadium>> saveStadiumsList(@RequestBody List<Stadium> stadiums) {
        List<Stadium> dbStadiums = stadiumService.saveStadiumsList(stadiums);
        return ResponseEntity.status(201).body(dbStadiums);
    }

    @PostMapping("/save")
    public ResponseEntity<Stadium> saveStadium(@RequestBody Stadium stadium) {
        Stadium dbStadium = stadiumService.saveStadium(stadium);
        return ResponseEntity.status(201).body(stadium);
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
