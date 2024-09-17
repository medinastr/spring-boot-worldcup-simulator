package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.dto.NationDTO;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NationApi implements com.medinastr.worldcup.api.NationApi {

    private final NationService nationService;

    @Autowired
    public NationApi(NationService nationService) {
        this.nationService = nationService;
    }

    @Override
    public ResponseEntity<List<NationDTO>> nationsList() {
        List<NationDTO> nations = nationService.getNations();
        return ResponseEntity.status(200).body(nations);
    }

    @Override
    public ResponseEntity<Nation> saveNation(@RequestBody NationDTO nationDTO) {
        Nation dbNation = nationService.saveNation(nationDTO);
        return ResponseEntity.status(201).body(dbNation);
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
