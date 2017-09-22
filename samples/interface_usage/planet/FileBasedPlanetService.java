package com.github.ledoyen.automocker.examples.esiea;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileBasedPlanetService implements PlanetService {

    @Override
    public List<Planet> listPlanets() {
        try {
            return Files
                    .lines(
                            Paths.get(
                                    FileBasedPlanetService.class
                                            .getClassLoader()
                                            .getResource("planet.txt")
                                            .toURI()
                            )
                    )
                    .map(s -> {
                        String[] items = s.split(" ");
                        return new Planet(items[0], Integer.valueOf(items[1]));
                    })
                    .collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
