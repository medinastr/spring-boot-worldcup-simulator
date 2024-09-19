package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.api.StadiumApi;
import com.medinastr.worldcup.entity.Stadium;
import com.medinastr.worldcup.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StadiumController implements StadiumApi {

    private final StadiumService stadiumService;

    @Autowired
    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @Override
    public ResponseEntity<List<Stadium>> getList() {
        List<Stadium> stadiums = stadiumService.getStadiumsList();
        return ResponseEntity.status(200).body(stadiums);
    }

    @Override
    public ResponseEntity<Stadium> getByName(@RequestParam String name) {
        Stadium stadium = stadiumService.getStadium(name);
        return ResponseEntity.status(200).body(stadium);
    }

    @Override
    public ResponseEntity<List<Stadium>> saveAll(@RequestBody List<Stadium> stadiums) {
        List<Stadium> dbStadiums = stadiumService.saveStadiumsList(stadiums);
        return ResponseEntity.status(201).body(dbStadiums);
    }

    @Override
    public ResponseEntity<Stadium> save(@RequestBody Stadium stadium) {
        Stadium dbStadium = stadiumService.saveStadium(stadium);
        return ResponseEntity.status(201).body(stadium);
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable String id) {
        stadiumService.delete(id);
        return ResponseEntity.status(204).build();
    }
}
