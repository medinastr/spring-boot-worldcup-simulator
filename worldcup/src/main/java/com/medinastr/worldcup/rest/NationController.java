package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.api.NationApi;
import com.medinastr.worldcup.dto.NationDTO;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NationController implements NationApi {

    private final NationService nationService;

    @Autowired
    public NationController(NationService nationService) {
        this.nationService = nationService;
    }

    @Override
    public ResponseEntity<List<NationDTO>> nationsList() {
        List<NationDTO> nations = nationService.getNationsList();
        return ResponseEntity.status(200).body(nations);
    }

    @Override
    public ResponseEntity<NationDTO> getNation(String id) {
        NationDTO nationDTO = nationService.getNation(id);
        return ResponseEntity.status(200).body(nationDTO);
    }

    @Override
    public ResponseEntity<NationDTO> saveNation(@RequestBody NationDTO nationDTO) {
        NationDTO dbNation = nationService.saveNation(nationDTO);
        return ResponseEntity.status(201).body(nationDTO);
    }

    @Override
    public ResponseEntity<List<Nation>> saveNationsList(@RequestBody List<NationDTO> nationsDTO) {
        List<Nation> dbNations = nationService.saveNationsList(nationsDTO);
        return ResponseEntity.status(201).body(dbNations);
    }

    @Override
    public ResponseEntity<?> deleteNation(@PathVariable String id) {
        nationService.delete(id);
        return ResponseEntity.status(204).build();
    }
}
