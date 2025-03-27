package com.fintonic.fintonicbase.factory;

public interface ShootingProtocolFactory {
    static ShootingProtocol getProtocolToApply(String protocol) {
        switch (protocol) {
            case "closest-enemies":
                return new ClosestEnemies();
            case "furthest-enemies":
                return new FurthestEnemies();
            case "assist-allies":
                return new AssistAllies();
            case "avoid-mech":
                return new AvoidMech();
            default:
                throw new IllegalArgumentException("Protocol not supported: " + protocol);
        }
    }
}
