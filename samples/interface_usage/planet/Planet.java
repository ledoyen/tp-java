package com.github.ledoyen.automocker.examples.esiea;

public class Planet {

    public final String name;
    public final int distance;

    public Planet(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return name  + " (" + distance + ")";
    }
}
