package com.github.ledoyen.automocker.examples.fruit;

public class Launcher {

    public static void main(String[] args) {
        Nourriture nourriture = getNourriture();

        System.out.println(nourriture.nom() + " : " + nourriture.prix() + "$$");
    }

    public static Nourriture getNourriture(){
//        return new Fruit("orange");
//        return  new Caramel();
        return new Hamburger("maxi best of plus bigmac");
    }
}
