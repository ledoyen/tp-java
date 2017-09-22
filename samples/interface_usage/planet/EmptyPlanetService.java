package com.github.ledoyen.automocker.examples.esiea;

import java.util.Collections;
import java.util.List;

public class EmptyPlanetService implements PlanetService {
    @Override
    public List<Planet> listPlanets() {
        return Collections.emptyList();
    }
}
