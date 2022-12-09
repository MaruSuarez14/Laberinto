package com.liceu.maze.model;

public class Coin implements Item{
    //Identificador de la moneda
    private String name;
    //Coordenadas donde se encuentra la moneda en el mapa
    private double coorX;
    private double coorY;
    //Determina si la moneda ha sido o no recolectada
    private boolean collected;

    public Coin(String name, double coorX, double coorY, boolean collected) {
        this.name = name;
        this.coorX = coorX;
        this.coorY = coorY;
        this.collected = collected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public double getCoorX() {
        return coorX;
    }

    public void setCoorX(double coorX) {
        this.coorX = coorX;
    }

    public double getCoorY() {
        return coorY;
    }

    public void setCoorY(double coorY) {
        this.coorY = coorY;
    }

    @Override
    public boolean isCollected() {
        return collected;
    }
}
