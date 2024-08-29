package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.dao.NationRepository;
import com.medinastr.worldcup.dto.NationDTO;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin("*")  // liberar todas as entradas que vierem da máquina
@RequestMapping("/nations")
public class NationRestController {

    NationService nationService;

    @Autowired
    public NationRestController(NationService nationService) {
        this.nationService = nationService;
    }

    @GetMapping
    public ResponseEntity<List<NationDTO>> nationsList() {
        List<NationDTO> nations = nationService.getNations();
        return ResponseEntity.status(200).body(nations);
    }

    @PostMapping("/save")
    public ResponseEntity<Nation> addNation(@RequestBody NationDTO nationDTO) {
        Nation dbNation = nationService.save(nationDTO);
        return ResponseEntity.status(201).body(dbNation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Nation>> deleteNation(@PathVariable int id) {
        Optional<Nation> nationToDelete = nationService.delete(id);
        return ResponseEntity.status(204).body(nationToDelete);
    }
}
