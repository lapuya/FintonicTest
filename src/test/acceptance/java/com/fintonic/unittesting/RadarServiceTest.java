package com.fintonic.unittesting;

import com.fintonic.fintonicbase.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RadarServiceTest {

    @InjectMocks
    RadarService radarService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_one_closest_enemy_protocol_return_same_enemy() {
        List<String> protocols = new ArrayList<>();
        List<ScanDto> scan = new ArrayList<>();
        EnemiesDto enemiesDto = new EnemiesDto(EnemiesType.SOLDIER, 10);
        ScanDto scanDto = new ScanDto(new CoordinatesDto(0, 40), enemiesDto, null);

        protocols.add("closest-enemies");
        scan.add(scanDto);

        RequestDto requestDto = new RequestDto(protocols, scan);

        var coordinatesDto = radarService.processRequest(requestDto);
        assertEquals(scanDto.coordinates(), coordinatesDto);
    }

    @Test
    void given_more_than_one_closest_enemy_protocol_closest_enemy() {
        List<String> protocols = new ArrayList<>();
        List<ScanDto> scan = new ArrayList<>();
        EnemiesDto enemiesDto = new EnemiesDto(EnemiesType.SOLDIER, 10);
        ScanDto scanDto1 = new ScanDto(new CoordinatesDto(0, 40), enemiesDto, null);
        ScanDto scanDto2 = new ScanDto(new CoordinatesDto(0, 30), enemiesDto, null);


        protocols.add("closest-enemies");
        scan.add(scanDto1);
        scan.add(scanDto2);

        RequestDto requestDto = new RequestDto(protocols, scan);

        var coordinatesDto = radarService.processRequest(requestDto);
        assertEquals(scanDto2.coordinates(), coordinatesDto);
    }
}