package com.fintonic.fintonicbase;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RadarService {

    public CoordinatesDto processRequest(RequestDto requestDto) {
        CoordinatesDto coordinatesDto;
        //aceptamos solo un protocolo por ahora
        switch (requestDto.protocols().get(0)) {
            case "closest-enemies":
                coordinatesDto = calculateClosestEnemy(requestDto);
                break;
            case "furthest-enemies":
                coordinatesDto = calculateFurthestEnemy(requestDto);
                break;
            default:
                coordinatesDto = new CoordinatesDto(0, 0); //excepción, tiramos 0,0 por ahora
        }
        return coordinatesDto;
    }

    private CoordinatesDto calculateClosestEnemy(RequestDto requestDto) {

        List<CoordinatesDto> listOfCoordinates = new ArrayList<>();

        for (ScanDto scanDto : requestDto.scan()) {
            listOfCoordinates.add(scanDto.coordinates());
        }
        listOfCoordinates.sort(Comparator.comparingInt(RadarService::distance));

        return listOfCoordinates.get(0);
    }

    private CoordinatesDto calculateFurthestEnemy(RequestDto requestDto) {
        List<CoordinatesDto> listOfCoordinates = new ArrayList<>();

        for (ScanDto scanDto : requestDto.scan()) {
            listOfCoordinates.add(scanDto.coordinates());
        }
        listOfCoordinates.sort(Comparator.comparingInt(RadarService::distance).reversed());

        return listOfCoordinates.get(0);
    }

    private static int distance(CoordinatesDto coordinates) {
        return (int) Math.sqrt(coordinates.x() * coordinates.x() + coordinates.y() * coordinates.y());
    }
}
