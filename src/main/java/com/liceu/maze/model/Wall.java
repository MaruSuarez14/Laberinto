package com.liceu.maze.model;

public class Wall implements MapSite{
    //Método para atravesar un muro (no se puede y devuelve un mensaje)
    @Override
    public String enter(Player player) {
        return "You cant can't go through a wall";
    }
}
