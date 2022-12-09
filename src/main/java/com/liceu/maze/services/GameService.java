package com.liceu.maze.services;

import com.liceu.maze.model.*;
import com.liceu.maze.utils.ParseToJSON;
import org.json.simple.JSONObject;

import java.util.List;

public class GameService {
    private static Game game;
    //Variable para guardar el tiempo de inicio del juego
    public static double start_time;
    //Variable para guardar el tiempo de fin del juego
    public static double end_time;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    //Método para comenzar una partida
    public JSONObject start (String mapID){
        //Se crea el juego a partir de la clase GameBuilder
        game = GameBuilder.createGame(mapID);
        //Se asigna un tiempo de inicio de partida
        start_time = (double) System.currentTimeMillis();
        //Se devuelve el objeto JSON parseado
        return ParseToJSON.parse(game);
    }

    //Método para moverse de una habitación a otra
    public JSONObject move (String dir) {
        //Se obtiene la habitación actual del jugador
        Room room = game.getPlayer().getCurrentRoom();
        //Se obtiene el MapSite de la habitación actual con la dirección pasada como parámetro
        MapSite ms = room.getSide(Maze.getDirection(dir));
        //Se entra a la habitación correspondiente con el método "enter" y se guarda el mensaje que devuelve
        String message = ms.enter(game.getPlayer());
        game.setMessage(message);
        //Si la habitación en la que se entra es la salida, se guarda el tiempo de fin de partida y se acaba el juego
        if(game.getPlayer().getCurrentRoom().isTarget()){
            end_time = (double) System.currentTimeMillis();
            game.setElapsedTime((int)(end_time - start_time)/1000);
            game.setEndGame(true);
        }
        return ParseToJSON.parse(game);
    }

    //Método para abrir una puerta
    public JSONObject open (String dir) {
        //Se obtiene la habitación actual del jugador
        Room room = game.getPlayer().getCurrentRoom();
        //Se obtiene el inventario del jugador
        List<Item> inventory = game.getPlayer().getInventory();
        //Se obtiene el MapSite de la habitación con la dirección recibida como argumento
        MapSite ms = room.getSide(Maze.getDirection(dir));
        boolean opened = false;
        Key key = null;
        //Se evalúa si el MapSite es una puerta
        if (ms.getClass().getSimpleName().equals("Door")){
            Door door = (Door) ms;
            //Se recorre el inventario del jugador y se evalúa si alguna de las llaves abre la puerta
            for (Item i:inventory) {
                if(i.getClass().getSimpleName().equals("Key")){
                    key = (Key) i;
                    if (key.equals(door.getKey())){
                        opened = door.open(key);
                    }
                }
            }
            //Si la puerta se ha abierto se elimina la llave del inventario del jugador
            if(opened){
                game.getPlayer().getInventory().remove(key);
                game.setMessage("You open a door");
            } else{
                game.setMessage("You can't open this door yet. Try to find a key");
            }
        }
        return ParseToJSON.parse(game);
    }

    //Método para obtener un item a partir de su nombre
    public JSONObject getItem (String name){
        //Se obtiene la habitación actual del jugador y la lista de items de éste
        Room room = game.getPlayer().getCurrentRoom();
        List<Item> items = room.getItemList();
        for (Item i : items) {
            //Si el objeto es una moneda
            if(i.getClass().getSimpleName().equals("Coin")){
                Coin coin = (Coin) i;
                //Se compara con el nombre recibido, siempre y cuando la moneda no esté recolectada
                if(coin.getName().equals(name) && !coin.isCollected()){
                    //Se colecta la moneda y se añade al inventario del jugador
                    coin.setCollected(true);
                    game.getPlayer().addItem(coin);
                    game.setMessage("You got a coin");
                }
            //Si el objeto es una llave
            }else if(i.getClass().getSimpleName().equals("Key")){
                Key key = (Key) i;
                //Se compara con el nombre recibido, si la moneda no está recolectada y la suma de monedas del jugador es mayor
                // o igual al coste de la moneda
                if(key.getName().equals(name) && game.getPlayer().getCoinSum() >= key.getCost() && !key.isCollected()){
                    key.setCollected(true);
                    //Se añade la llave al inventario y se resta las monedas correspondientes
                    game.getPlayer().addItem(key);
                    game.getPlayer().consumeCoin(key.getCost());
                    game.setMessage("You got a key");
                //Si las monedas del jugador es menor que el coste de la moneda, se escribe un mensaje
                }else if (key.getName().equals(name) && game.getPlayer().getCoinSum() < key.getCost() && !key.isCollected()){
                    game.setMessage("You don't have enough money. This key costs " + key.getCost() + " coins");
                }
            }
        }
        return ParseToJSON.parse(game);
    }
}
