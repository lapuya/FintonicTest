package com.fintonic.fintonicbase;

import java.util.List;
public record RequestDto(List<String> protocols, List<ScanDto> scan) {


}
