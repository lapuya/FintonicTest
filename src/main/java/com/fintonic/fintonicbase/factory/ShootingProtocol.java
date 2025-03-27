package com.fintonic.fintonicbase.factory;

import com.fintonic.fintonicbase.ScanDto;

import java.util.List;

public interface ShootingProtocol {
    void applyProtocol(List<ScanDto> listOfScan);
}
