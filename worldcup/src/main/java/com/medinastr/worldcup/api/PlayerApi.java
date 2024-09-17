package com.medinastr.worldcup.api;

import com.medinastr.worldcup.dto.PlayerDTO;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.entity.Player;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/players")
@Tag(name="Player", description = "Endpoints for mapping soccer players")
public interface PlayerApi {

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Find all players", tags = {"Player"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Player.class)))})
            })
    public ResponseEntity<List<PlayerDTO>> getPlayers();

    @PostMapping(value = "/save",
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Save a single player", tags = {"Player"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = Nation.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ResponseEntity<Player> savePlayer(@RequestBody PlayerDTO playerDTO);

    @PostMapping(value = "/saveAll",
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Save new players", tags = {"Player"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = Nation.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ResponseEntity<List<Player>> savePlayersList(@RequestBody List<PlayerDTO> playersDTO);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a player", tags = {"Player"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<?> deletePlayer(@PathVariable String id);
}
