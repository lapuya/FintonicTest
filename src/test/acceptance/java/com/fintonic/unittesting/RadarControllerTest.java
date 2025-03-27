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

public class RadarControllerTest {

    @InjectMocks
    RadarController radarController;

    @Mock
    RadarService radarService;

    private static CoordinatesDto defaultCoordinates = new CoordinatesDto(0, 0);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_a_request_return_coordinates() {
        List<String> protocols = new ArrayList<>();
        List<ScanDto> scan = new ArrayList<>();
        RequestDto requestDto = new RequestDto(protocols, scan);

        when(radarService.processRequest(any(RequestDto.class))).thenReturn(defaultCoordinates);

        var coordinatesDto = radarController.calculateCoordinates(requestDto);

        assertEquals(HttpStatus.OK, coordinatesDto.getStatusCode());
        assertEquals(defaultCoordinates, coordinatesDto.getBody());
    }
}