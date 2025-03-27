package com.fintonic.fintonicbase;

import org.springframework.stereotype.Service;

@Service
public class RadarService {

    public CoordinatesDto processRequest(RequestDto requestDto) {
        return new CoordinatesDto(0, 0);
    }
}
