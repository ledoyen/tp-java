package com.github.ledoyen.automocker.examples.esiea;

import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        PlanetService planetService = PlanetServiceFactory.getPlanetService();

        List<Planet> planets = planetService.listPlanets();
        System.out.println("There is " + planets.size() + " planets\n");
        planets.forEach(planet -> System.out.println(planet));
    }
}
