package com.fintonic.fintonicbase;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RadarService {

    public CoordinatesDto processRequest(RequestDto requestDto) {
        List<CoordinatesDto> listOfCoordinates = new ArrayList<>();

        for (ScanDto scanDto : requestDto.scan()) {
            listOfCoordinates.add(scanDto.coordinates());
        }
        listOfCoordinates.sort(Comparator.comparingInt(RadarService::distance));

        return listOfCoordinates.get(0);
    }

    private static int distance(CoordinatesDto coordinates) {
        return (int) Math.sqrt(coordinates.x() * coordinates.x() + coordinates.y() * coordinates.y());
    }
}
