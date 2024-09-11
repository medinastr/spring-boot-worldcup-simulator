package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.entity.Stadium;
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
    public ResponseEntity<String> handleException(RuntimeException exc) {
        return ResponseEntity.status(400).body(exc.getMessage());
    }
}
