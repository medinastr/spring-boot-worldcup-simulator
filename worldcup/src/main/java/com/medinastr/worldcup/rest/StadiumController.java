package com.medinastr.worldcup.rest;

import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.entity.Player;
import com.medinastr.worldcup.entity.Stadium;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/stadiums")
@Tag(name="Stadium", description = "Endpoints for mapping soccer stadiums")
public interface StadiumController {

    @GetMapping
    @Operation(summary = "Find all stadiums", tags = {"Stadium"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Player.class)))})
            })
    public ResponseEntity<List<Stadium>> getStadiumsList();

    @GetMapping("/find")
    @Operation(summary = "Find a stadium", tags = {"Stadium"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = Player.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ResponseEntity<Stadium> getStadium(@RequestParam String name);

    @PostMapping("/save")
    @Operation(summary = "Save a single stadium", tags = {"Stadium"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = Nation.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ResponseEntity<Stadium> saveStadium(@RequestBody Stadium stadium);

    @PostMapping("/saveAll")
    @Operation(summary = "Save new stadiums", tags = {"Stadium"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = Nation.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ResponseEntity<List<Stadium>> saveStadiumsList(@RequestBody List<Stadium> stadiums);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a nation", tags = {"Stadium"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<?> deleteStadium(@PathVariable String id);
}
