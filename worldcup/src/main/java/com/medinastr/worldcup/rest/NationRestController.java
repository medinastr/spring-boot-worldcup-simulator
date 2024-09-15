package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.dto.NationDTO;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.service.NationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nations")
@Tag(name="Nation", description = "Endpoints for mapping soccer nations")
public class NationRestController {

    private final NationService nationService;

    @Autowired
    public NationRestController(NationService nationService) {
        this.nationService = nationService;
    }

    @GetMapping
    @Operation(summary = "Finds all nations",
            tags = {"Nation"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = {
                        @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = NationDTO.class))
                        )}
                )
            }
    )
    public ResponseEntity<List<NationDTO>> nationsList() {
        List<NationDTO> nations = nationService.getNations();
        return ResponseEntity.status(200).body(nations);
    }

    @PostMapping("/save")
    @Operation(summary = "Create new nation", tags = {"Nation"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = Nation.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ResponseEntity<Nation> saveNation(@RequestBody NationDTO nationDTO) {
        Nation dbNation = nationService.saveNation(nationDTO);
        return ResponseEntity.status(201).body(dbNation);
    }

    @PostMapping("/saveAll")
    @Operation(summary = "Create new nation", tags = {"Nation"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = NationDTO.class)))
                    ),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ResponseEntity<List<Nation>> saveNationsList(@RequestBody List<NationDTO> nationsDTO) {
        List<Nation> dbNations = nationService.saveNationsList(nationsDTO);
        return ResponseEntity.status(201).body(dbNations);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a nation", tags = {"Nation"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<Optional<Nation>> deleteNation(@PathVariable int id) {
        Optional<Nation> nationToDelete = nationService.delete(id);
        return ResponseEntity.status(204).body(nationToDelete);
    }
}
