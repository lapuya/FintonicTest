package com.fintonic.unittesting;

import com.fintonic.fintonicbase.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void given_more_than_one_enemies_and_protocol_closest_enemy_case_1() {
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

    @Test
    void given_more_than_one_enemies_and_protocol_closest_enemy_case_2() {
        List<String> protocols = new ArrayList<>();
        List<ScanDto> scan = new ArrayList<>();
        EnemiesDto enemiesDto = new EnemiesDto(EnemiesType.SOLDIER, 10);
        ScanDto scanDto1 = new ScanDto(new CoordinatesDto(41, 3), enemiesDto, null);
        ScanDto scanDto2 = new ScanDto(new CoordinatesDto(0, 30), enemiesDto, null);
        ScanDto scanDto3 = new ScanDto(new CoordinatesDto(2, 3), enemiesDto, null);
        ScanDto scanDto4 = new ScanDto(new CoordinatesDto(30, 0), enemiesDto, null);

        protocols.add("closest-enemies");
        scan.add(scanDto1);
        scan.add(scanDto2);
        scan.add(scanDto3);
        scan.add(scanDto4);

        RequestDto requestDto = new RequestDto(protocols, scan);

        var coordinatesDto = radarService.processRequest(requestDto);
        assertEquals(scanDto3.coordinates(), coordinatesDto);
    }

    @Test
    void given_one_furthest_enemy_protocol_return_same_enemy() {
        List<String> protocols = new ArrayList<>();
        List<ScanDto> scan = new ArrayList<>();
        EnemiesDto enemiesDto = new EnemiesDto(EnemiesType.SOLDIER, 10);
        ScanDto scanDto = new ScanDto(new CoordinatesDto(0, 40), enemiesDto, null);

        protocols.add("furthest-enemies");
        scan.add(scanDto);

        RequestDto requestDto = new RequestDto(protocols, scan);

        var coordinatesDto = radarService.processRequest(requestDto);
        assertEquals(scanDto.coordinates(), coordinatesDto);
    }

    @Test
    void given_more_than_one_enemies_and_protocol_furthest_enemy_case_1() {
        List<String> protocols = new ArrayList<>();
        List<ScanDto> scan = new ArrayList<>();
        EnemiesDto enemiesDto = new EnemiesDto(EnemiesType.SOLDIER, 10);
        ScanDto scanDto1 = new ScanDto(new CoordinatesDto(0, 40), enemiesDto, null);
        ScanDto scanDto2 = new ScanDto(new CoordinatesDto(0, 30), enemiesDto, null);


        protocols.add("furthest-enemies");
        scan.add(scanDto1);
        scan.add(scanDto2);

        RequestDto requestDto = new RequestDto(protocols, scan);

        var coordinatesDto = radarService.processRequest(requestDto);
        assertEquals(scanDto1.coordinates(), coordinatesDto);
    }

    @Test
    void given_more_than_one_enemies_and_protocol_furthest_enemy_case_2() {
        List<String> protocols = new ArrayList<>();
        List<ScanDto> scan = new ArrayList<>();
        EnemiesDto enemiesDto = new EnemiesDto(EnemiesType.SOLDIER, 10);
        ScanDto scanDto1 = new ScanDto(new CoordinatesDto(41, 3), enemiesDto, null);
        ScanDto scanDto2 = new ScanDto(new CoordinatesDto(0, 30), enemiesDto, null);
        ScanDto scanDto3 = new ScanDto(new CoordinatesDto(30, 40), enemiesDto, null);
        ScanDto scanDto4 = new ScanDto(new CoordinatesDto(30, 0), enemiesDto, null);

        protocols.add("furthest-enemies");
        scan.add(scanDto1);
        scan.add(scanDto2);
        scan.add(scanDto3);
        scan.add(scanDto4);

        RequestDto requestDto = new RequestDto(protocols, scan);

        var coordinatesDto = radarService.processRequest(requestDto);
        assertEquals(scanDto3.coordinates(), coordinatesDto);
    }
}