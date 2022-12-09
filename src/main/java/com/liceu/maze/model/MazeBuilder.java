package com.liceu.maze.model;

public interface MazeBuilder {
    //Método para crear una habitación y añadirla al laberinto
    void buildRoom(int roomID);
    //Método para determinar la habitación que tiene la salida del laberinto
    void setTarget(int roomID);
    //Método para crear una puerta entre dos habitaciones
    void buildDoor(int roomFrom, int roomTo, Maze.Directions dir);
    //Método para crear una puerta entre dos habitaciones, cerrando dicha puerta con una llave
    void buildDoor(int roomFrom, int roomTo, Maze.Directions dir, Key key);
    //Método para colocar una llave en una habitación
    void putKeyInRoom(int roomID, Key key);
    //Método para colocar una moneda en una habitación
    void putCoinInRoom(int roomID, Coin coin);
    //Método que devuelve el laberinto creado
    Maze getMaze();
}
