package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.entity.Stadium;
import com.medinastr.worldcup.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StadiumApi implements com.medinastr.worldcup.api.StadiumApi {

    private final StadiumService stadiumService;

    @Autowired
    public StadiumApi(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @Override
    public ResponseEntity<List<Stadium>> getStadiumsList() {
        List<Stadium> stadiums = stadiumService.getStadiumsList();
        return ResponseEntity.status(200).body(stadiums);
    }

    @Override
    public ResponseEntity<Stadium> getStadium(@RequestParam String name) {
        Stadium stadium = stadiumService.getStadium(name);
        return ResponseEntity.status(200).body(stadium);
    }

    @Override
    public ResponseEntity<List<Stadium>> saveStadiumsList(@RequestBody List<Stadium> stadiums) {
        List<Stadium> dbStadiums = stadiumService.saveStadiumsList(stadiums);
        return ResponseEntity.status(201).body(dbStadiums);
    }

    @Override
    public ResponseEntity<Stadium> saveStadium(@RequestBody Stadium stadium) {
        Stadium dbStadium = stadiumService.saveStadium(stadium);
        return ResponseEntity.status(201).body(stadium);
    }

    @Override
    public ResponseEntity<?> deleteStadium(@PathVariable String id) {
        stadiumService.delete(id);
        return ResponseEntity.status(204).build();
    }
}
