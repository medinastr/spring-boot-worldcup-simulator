package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.dao.NationRepository;
import com.medinastr.worldcup.entity.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nations")
public class NationRestController {

    NationRepository nationRepository;

    @Autowired
    public NationRestController(NationRepository nationRepository) {
        this.nationRepository = nationRepository;
    }

    @GetMapping
    public List<Nation> nationsList() {
        return (List<Nation>) nationRepository.findAll();
    }

    @PostMapping
    public Nation addNation(@RequestBody Nation nation) {
        return nationRepository.save(nation);
    }
}
