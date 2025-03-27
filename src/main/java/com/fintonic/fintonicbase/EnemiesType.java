package com.fintonic.fintonicbase;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EnemiesType {
    SOLDIER("soldier"),
    MECH("mech");

    private final String value;

    EnemiesType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static EnemiesType fromValue(String value) {
        for (EnemiesType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid enemy type: " + value);
    }
}
