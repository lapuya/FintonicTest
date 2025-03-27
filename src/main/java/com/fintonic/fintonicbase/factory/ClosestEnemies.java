package com.fintonic.fintonicbase.factory;

import com.fintonic.fintonicbase.RadarService;
import com.fintonic.fintonicbase.ScanDto;

import java.util.Comparator;
import java.util.List;

public class ClosestEnemies implements ShootingProtocol {

    @Override
    public void applyProtocol(List<ScanDto> listOfScan) {
        listOfScan.sort(Comparator.comparingInt(p -> RadarService.distance(p.coordinates())));
    }
}
