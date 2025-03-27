package com.fintonic.fintonicbase.factory;

import com.fintonic.fintonicbase.EnemiesType;
import com.fintonic.fintonicbase.ScanDto;

import java.util.List;

public class AvoidMech implements ShootingProtocol {

    @Override
    public void applyProtocol(List<ScanDto> listOfScan) {
        // assume mech and allies protocol cant be together, since in that case we wont be saving allies
        listOfScan.removeIf(p -> EnemiesType.MECH.equals(p.enemies().type()));
    }
}
