package com.fintonic.fintonicbase;

import com.fintonic.fintonicbase.factory.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadarService {

    private final static CoordinatesDto DEFAULT_COORDINATES = new CoordinatesDto(0, 0);
    public CoordinatesDto processRequest(RequestDto requestDto) {
        List<ScanDto> listOfScan = requestDto.scan();

        for (String protocol : requestDto.protocols()) {
            ShootingProtocol shootingProtocolToApply = ShootingProtocolFactory.getProtocolToApply(protocol);
            shootingProtocolToApply.applyProtocol(listOfScan);
        }

        listOfScan.removeIf(p -> RadarService.distance(p.coordinates()) > 100);

        checkEmpty(listOfScan);

        return listOfScan.get(0).coordinates();
    }

    private void checkEmpty(List<ScanDto> listOfScan) {
        if (listOfScan.isEmpty()) {
            listOfScan.add(new ScanDto(DEFAULT_COORDINATES, null, null));
        }
    }

    public static int distance(CoordinatesDto coordinates) {
        return (int) Math.sqrt(coordinates.x() * coordinates.x() + coordinates.y() * coordinates.y());
    }
}
