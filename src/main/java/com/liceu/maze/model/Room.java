package com.liceu.maze.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    //Identificador de la habitación
    private int RoomID;
    //Lista de Items que se encuentran en la habitación
    private List<Item> items = new ArrayList<>();
    //Lados que tiene una habitación (Hashmap de una dirección y un MapSite)
    private Map<Maze.Directions, MapSite> sides = new HashMap<>();
    //Determina si es la habitación para salir del laberinto
    private boolean target = false;

    public Room(int roomID) {
        RoomID = roomID;
    }

    public boolean isTarget() {
        return target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public void addItem(Item it) {
        this.items.add(it);
    }

    public List<Item> getItemList() {
        return this.items;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int roomID) {
        RoomID = roomID;
    }

    public MapSite getSide(Maze.Directions dir) {
        return this.sides.get(dir);
    }

    public void setSide(Maze.Directions dir, MapSite ms) {
        this.sides.put(dir, ms);
    }



}
