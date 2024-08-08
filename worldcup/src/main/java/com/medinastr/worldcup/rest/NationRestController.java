package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.dao.NationRepository;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")  // liberar todas as entradas que vierem da máquina
@RequestMapping("/nations")
public class NationRestController {

    NationService nationService;

    public NationRestController(NationService nationService) {
        this.nationService = nationService;
    }

    @GetMapping
    public ResponseEntity<List<Nation>> nationsList() {
        List<Nation> nations = (List<Nation>) nationService.findAll();
        return ResponseEntity.status(200).body(nations);
    }

    @PostMapping
    public ResponseEntity<Nation> addNation(@RequestBody Nation nation) {
        Nation dbNation = nationService.save(nation);
        return ResponseEntity.status(201).body(dbNation);
    }

    @PutMapping
    public ResponseEntity<Nation> updateNation(@RequestBody Nation nation) {
        Nation dbNation = nationService.save(nation);
        return ResponseEntity.status(201).body(dbNation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Nation>> deleteNation(@PathVariable int id) {
        Optional<Nation> nationToDelete = nationService.delete(id);
        return ResponseEntity.status(204).body(nationToDelete);
    }
}
