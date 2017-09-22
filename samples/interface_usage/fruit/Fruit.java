package com.github.ledoyen.automocker.examples.fruit;

public class Fruit implements Nourriture {

    private final String nom;

    public Fruit(String nom) {
        this.nom = nom;
    }

    @Override
    public String nom() {
        return nom;
    }

    @Override
    public int prix() {
        return nom.length();
    }
}
