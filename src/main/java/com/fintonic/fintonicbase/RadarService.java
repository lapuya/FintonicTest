package com.fintonic.fintonicbase;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class RadarService {

    private final static CoordinatesDto DEFAULT_COORDINATES = new CoordinatesDto(0, 0);
    public CoordinatesDto processRequest(RequestDto requestDto) {
        List<ScanDto> listOfScan = requestDto.scan();

        if (requestDto.protocols().contains("closest-enemies")) {
            listOfScan.sort(Comparator.comparingInt(p -> distance(p.coordinates())));
        }

        if (requestDto.protocols().contains("furthest-enemies")) {
            listOfScan.sort(Comparator.comparingInt((ScanDto p) -> distance(p.coordinates())).reversed());
        }

        if (requestDto.protocols().contains("assist-allies")) {
            //can be null or number, if provided, can be 0
            listOfScan.removeIf(p -> Objects.isNull(p.allies()) || p.allies().number() == 0);
        }

        // entiendo que no puede haber mech y allies a la vez, porque entonces no se socorrería a los aliados
        if (requestDto.protocols().contains("avoid-mech")) {
            return DEFAULT_COORDINATES;
        }

        return listOfScan.get(0).coordinates();
    }

    private static int distance(CoordinatesDto coordinates) {
        return (int) Math.sqrt(coordinates.x() * coordinates.x() + coordinates.y() * coordinates.y());
    }
}
