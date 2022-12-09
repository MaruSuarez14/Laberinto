package com.liceu.maze.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    //Habitación actual dónde se encuentra el jugador
    private Room currentRoom;
    //Inventario del jugador (lista de Items)
    private final List<Item> inventory = new ArrayList<>();
    //Nombre del jugador
    private String name;

    //Método para obtener el número total de monedas
    public int getCoinSum(){
        //Recorre el inventario y determina qué items son monedas y las suma
        int result = 0;
        for (Item e : this.inventory) {
            if(e.getClass().getSimpleName().equals("Coin")){
                result ++;
            }
        }
        return result;
    }

    //Método para consumir las monedas a partir de una cantidad
    public void consumeCoin(int amount){
        List<Coin> coinList = new ArrayList<>();
        //Recorre el inventario y añade las monedas a una lista
        for (Item e : this.inventory) {
            if(e.getClass().getSimpleName().equals("Coin")) {
                Coin coin = (Coin) e;
                coinList.add(coin);
            }
        }
        //Crea una sublista con la cantidad de monedas que nos envían por parámetro
        coinList = coinList.subList(0,amount);
        //Elimina del inventario los elementos que hay en la sublista
        this.inventory.removeAll(coinList);
    }

    public void addItem(Item it) {
        this.inventory.add(it);
    }

    public List<Item> getInventory() {
        return this.inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
