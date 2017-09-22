package com.github.ledoyen.automocker.examples.fruit;

public class Caramel implements Nourriture {
    @Override
    public String nom() {
        return "caramel";
    }

    @Override
    public int prix() {
        return 42;
    }
}
