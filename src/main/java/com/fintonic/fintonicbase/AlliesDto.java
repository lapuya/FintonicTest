package com.fintonic.fintonicbase;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record AlliesDto(@JsonValue Integer number) {

    @JsonCreator
    public AlliesDto(Integer number) {
        this.number = number;
    }
}
