package com.liceu.maze.model;

public class Door implements MapSite {
    //Llave que abre la puerta
    private Key key;
    //Habitaciones que conecta la puerta
    private final Room room1, room2;
    //Determina si la puerta está cerrada
    private boolean locked = false;

    public Door(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
    }

    public boolean isLocked() {
        return locked;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    //Método para abrir una puerta a partir de una llave
    public boolean open(Key key) {
        //Si la puerta se abre devuelve true, sino devuelve false
        if (this.key.equals(key)) {
            this.locked = false;
            return true;
        } else {
            return false;
        }
    }

    //Método para cerrar una puerta
    public void close() {
        this.locked = true;
    }

    //Método para atravesar una puerta
    @Override
    public String enter(Player player) {
        //Si la puerta no está bloqueada, el jugador irá a la habitación contigua de la puerta
        if (!this.locked) {
            Room r = getOtherRoom(player.getCurrentRoom());
            player.setCurrentRoom(r);
            return "";
        //Si la puerta está bloqueada, lo avisa al jugador
        } else {
            return "This door is locked";
        }
    }

    //Método para obtener la habitación contigua a la puerta
    private Room getOtherRoom(Room currentRoom) {
        //Entre las dos habitaciones que conecta una puerta, se devuelve la habitación en la que no está el jugador
        if (room1.getRoomID() == currentRoom.getRoomID()) {
            return room2;
        }
        return room1;
    }
}
