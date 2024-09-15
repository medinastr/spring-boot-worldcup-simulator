package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.entity.Player;
import com.medinastr.worldcup.entity.Stadium;
import com.medinastr.worldcup.service.StadiumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Find all stadiums", tags = {"Stadium"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Player.class)))})
            })
    public ResponseEntity<List<Stadium>> getStadiumsList() {
        List<Stadium> stadiums = stadiumService.getStadiumsList();
        return ResponseEntity.status(200).body(stadiums);
    }

    @GetMapping("/find")
    @Operation(summary = "Find a stadium", tags = {"Stadium"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = Player.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ResponseEntity<Stadium> getStadium(@RequestParam String name) {
        Stadium stadium = stadiumService.getStadium(name);
        return ResponseEntity.status(200).body(stadium);
    }

    @PostMapping("/saveAll")
    @Operation(summary = "Save new stadiums", tags = {"Stadium"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = Nation.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    ResponseEntity<List<Stadium>> saveStadiumsList(@RequestBody List<Stadium> stadiums) {
        List<Stadium> dbStadiums = stadiumService.saveStadiumsList(stadiums);
        return ResponseEntity.status(201).body(dbStadiums);
    }

    @PostMapping("/save")
    @Operation(summary = "Save a single stadium", tags = {"Stadium"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = Nation.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ResponseEntity<Stadium> saveStadium(@RequestBody Stadium stadium) {
        Stadium dbStadium = stadiumService.saveStadium(stadium);
        return ResponseEntity.status(201).body(stadium);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a nation", tags = {"Nation"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<?> deleteStadium(@PathVariable String id) {
        stadiumService.delete(id);
        return ResponseEntity.status(204).build();
    }
}
