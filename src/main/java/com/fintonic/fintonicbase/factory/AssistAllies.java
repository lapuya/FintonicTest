package com.fintonic.fintonicbase.factory;

import com.fintonic.fintonicbase.ScanDto;

import java.util.List;
import java.util.Objects;

public class AssistAllies implements ShootingProtocol {

    @Override
    public void applyProtocol(List<ScanDto> listOfScan) {
        //can be null or number, if provided, can be 0
        listOfScan.removeIf(p -> Objects.isNull(p.allies()) || p.allies().number() == 0);
    }
}
