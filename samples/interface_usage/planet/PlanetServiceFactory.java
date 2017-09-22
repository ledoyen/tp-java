package com.github.ledoyen.automocker.examples.esiea;

public class PlanetServiceFactory {

    public static PlanetService getPlanetService() {
//        return new EmptyPlanetService();
        return new FileBasedPlanetService();
    }
}
