package com.medinastr.worldcup.api;

import com.medinastr.worldcup.dto.NationDTO;
import com.medinastr.worldcup.entity.Nation;
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

@RequestMapping("/api/v1/nations")
@Tag(name="Nation", description = "Endpoints for mapping soccer nations")
public interface NationApi {

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Finds all nations",
            tags = {"Nation"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = NationDTO.class)))})
            })
    public ResponseEntity<List<NationDTO>> nationsList();

    @PostMapping(value = "/save",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Create new nation", tags = {"Nation"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = Nation.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ResponseEntity<Nation> saveNation(@RequestBody NationDTO nationDTO);

    @PostMapping(value = "/saveAll",
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Create new nation", tags = {"Nation"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = NationDTO.class)))
                    ),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ResponseEntity<List<Nation>> saveNationsList(@RequestBody List<NationDTO> nationsDTO);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a nation", tags = {"Nation"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<?> deleteNation(@PathVariable String id);
}
