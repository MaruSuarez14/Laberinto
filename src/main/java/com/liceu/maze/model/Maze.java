package com.liceu.maze.model;

import java.util.HashMap;
import java.util.Map;

public class Maze {
    //Identificador del laberinto
    private String mazeID;
    //Direcciones del laberinto
    public enum Directions {
        NORTH, SOUTH, EAST, WEST
    }
    //Habitaciones del laberinto (Hashmap con el identificador de la puerta y la puerta en sí
    Map<Integer, Room> rooms = new HashMap<>();

    public String getMazeID() {
        return mazeID;
    }

    public void setMazeID(String mazeID) {
        this.mazeID = mazeID;
    }

    //Método para añadir una habitación al laberinto
    public void addRoom(int roomID, Room room) {
        this.rooms.put(roomID, room);
    }

    public Room getRoom(int roomID) {
        return this.rooms.get(roomID);
    }

    //Devuelve la dirección correspondiente a partir de un String (N, S, E, W)
    public static Directions getDirection (String s) {
        switch (s) {
            case "N":
                 return Directions.NORTH;
            case "S":
                return Directions.SOUTH;
            case "E":
                return Directions.EAST;
            case "W":
                return Directions.WEST;
            default:
                return null;
        }
    }

}
