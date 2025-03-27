package com.fintonic.fintonicbase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class RadarController {

    private final RadarService radarService;

    public RadarController(RadarService radarService) {
        this.radarService = radarService;
    }

    @PostMapping("/radar")
    public ResponseEntity<CoordinatesDto> calculateCoordinates(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(radarService.processRequest(requestDto));
    }
}
