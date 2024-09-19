package com.medinastr.worldcup.api;

import com.medinastr.worldcup.dto.NationDTO;
import com.medinastr.worldcup.entity.Nation;
import com.medinastr.worldcup.entity.Player;
import com.medinastr.worldcup.entity.Stadium;
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
public interface NationApi extends GenericApi<Nation, NationDTO> {

    @Override
    @Operation(summary = "Get a list of all nations", tags = {"Nation"})
    public ResponseEntity<List<NationDTO>> getList();

    @Override
    @Operation(summary = "Save one nation", tags = {"Nation"})
    public ResponseEntity<NationDTO> save(NationDTO nationDTO);

    @Override
    @Operation(summary = "Save a list nations", tags = {"Nation"})
    public ResponseEntity<List<Nation>> saveAll(List<NationDTO> nationDTOS);

    @Override
    @Operation(summary = "Delete a nation", tags = {"Nation"})
    public ResponseEntity<?> delete(String id);

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Get one nation by id", tags = {"Nation"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = Object.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content)
            })
    public ResponseEntity<NationDTO> getById(@PathVariable String id);
}
