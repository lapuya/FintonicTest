package com.fintonic.fintonicbase;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RadarService {
    private final static CoordinatesDto DEFAULT_COORDINATES = new CoordinatesDto(0, 0);

    public CoordinatesDto processRequest(RequestDto requestDto) {
        List<CoordinatesDto> listOfCoordinates = new ArrayList<>();

        for (ScanDto scanDto : requestDto.scan()) {
            listOfCoordinates.add(scanDto.coordinates());
        }

        if (requestDto.protocols().contains("closest-enemies")) {
            listOfCoordinates.sort(Comparator.comparingInt(RadarService::distance));
        }

        if (requestDto.protocols().contains("furthest-enemies")) {
            listOfCoordinates.sort(Comparator.comparingInt(RadarService::distance).reversed());
        }

        if (requestDto.protocols().contains("assist-allies")) {
            return DEFAULT_COORDINATES;
        }
        return listOfCoordinates.get(0);
    }

    private static int distance(CoordinatesDto coordinates) {
        return (int) Math.sqrt(coordinates.x() * coordinates.x() + coordinates.y() * coordinates.y());
    }
}
