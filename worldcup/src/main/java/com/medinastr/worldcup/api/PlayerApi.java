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
public interface PlayerApi extends GenericApi<Player, PlayerDTO> {

    @Override
    public ResponseEntity<List<PlayerDTO>> getList();

    @Override
    public ResponseEntity<PlayerDTO> save(@RequestBody PlayerDTO playerDTO);

    @Override
    public ResponseEntity<List<Player>> saveAll(@RequestBody List<PlayerDTO> playersDTO);

    @Override
    public ResponseEntity<?> delete(@PathVariable String id);
}
