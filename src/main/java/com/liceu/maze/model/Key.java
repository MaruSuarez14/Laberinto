package com.liceu.maze.model;

public class Key implements Item{

    //Identificador de la llave
    private String name;
    //Coordenadas donde se encuentra la llave en el mapa
    private double coorX;
    private double coorY;
    //Determina si la llave ha sido recolectada
    private boolean collected = false;
    //Precio de la llave
    private int cost;

    public Key(double coorX, double coorY, String name, int cost) {
        this.coorX = coorX;
        this.coorY = coorY;
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean isCollected() {
        return collected;
    }
}
