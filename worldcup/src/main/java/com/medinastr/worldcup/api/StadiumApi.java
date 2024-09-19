package com.medinastr.worldcup.api;

import com.medinastr.worldcup.dto.PlayerDTO;
import com.medinastr.worldcup.dto.StadiumDTO;
import com.medinastr.worldcup.entity.Player;
import com.medinastr.worldcup.entity.Stadium;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/stadiums")
@Tag(name="Stadium", description = "Endpoints for mapping soccer stadiums")
public interface StadiumApi extends GenericApi<Stadium, Stadium> {

    @Override
    @Operation(summary = "Get a list of all stadiums", tags = {"Stadium"})
    public ResponseEntity<List<Stadium>> getList();

    @Override
    @Operation(summary = "Save one stadium", tags = {"Stadium"})
    public ResponseEntity<Stadium> save(Stadium stadium);

    @Override
    @Operation(summary = "Save a list of all stadiums", tags = {"Stadium"})
    public ResponseEntity<List<Stadium>> saveAll(List<Stadium> stadiums);

    @Override
    @Operation(summary = "Delete one stadium by id", tags = {"Stadium"})
    public ResponseEntity<?> delete(String id);

    @GetMapping(value = "/{name}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Get one stadium by name", tags = {"Stadium"},
            responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = Object.class))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content)
    })
    public ResponseEntity<Stadium> getByName(@RequestParam String name);
}
