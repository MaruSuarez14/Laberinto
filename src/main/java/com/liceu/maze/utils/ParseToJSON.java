package com.liceu.maze.utils;

import com.liceu.maze.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseToJSON {

    //Método para parsear un objeto JSON con la información necesaria del juego
    public static JSONObject parse (Game game) {
        JSONObject gameJson = new JSONObject();
        JSONObject room = new JSONObject();
        JSONObject sides = new JSONObject();
        JSONObject items = new JSONObject();
        JSONArray coins = new JSONArray();
        JSONArray keys = new JSONArray();
        JSONObject inventory = new JSONObject();
        //Se añade el id de la habitación
        room.put("id", game.getPlayer().getCurrentRoom().getRoomID());
        //Se añaden los lados de la habitación
        sides.put("N",getMapSiteType(game.getPlayer().getCurrentRoom().getSide(Maze.Directions.NORTH)));
        sides.put("S",getMapSiteType(game.getPlayer().getCurrentRoom().getSide(Maze.Directions.SOUTH)));
        sides.put("E",getMapSiteType(game.getPlayer().getCurrentRoom().getSide(Maze.Directions.EAST)));
        sides.put("W",getMapSiteType(game.getPlayer().getCurrentRoom().getSide(Maze.Directions.WEST)));
        room.put("sides",sides);
        //Se añaden las monedas de los items de la habitación
        coins.addAll(getCoinList(game.getPlayer().getCurrentRoom().getItemList()));
        items.put("coins",coins);
        //Se añaden las llaves de los items de la habitación
        keys.addAll(getKeyList(game.getPlayer().getCurrentRoom().getItemList()));
        items.put("keys",keys);
        //Se añaden los items de la habitación
        room.put("items",items);
        //Se añade la habitación
        gameJson.put("room", room);
        //Se añade las monedas que tiene el jugador al inventario
        inventory.put("coins", game.getPlayer().getCoinSum());
        //Se añaden las llaves que tiene el jugador al inventario
        inventory.put("keys",game.getPlayer().getInventory()
                .stream()
                .filter( x -> x
                        .getClass()
                        .getSimpleName()
                        .equals("Key"))
                .count());
        //Se añade el inventario
        gameJson.put("inventory", inventory);
        //Se añade el mensaje del juego
        gameJson.put("message" , game.getMessage());
        //Se añade la variable para determinar si el juego ha terminado
        gameJson.put("endGame", game.isEndGame());
        return gameJson;
    }

    //Devuelve el tipo de MapSite que es (pared o puerta)
    private static String getMapSiteType(MapSite m){
        if (m.getClass().getSimpleName().equals("Wall")){
            return "wall";
        }
        //Si es una puerta, determina si está cerrada o abierta y devuelve el resultado
        if (m.getClass().getSimpleName().equals("Door")){
            Door d = (Door) m;
            return d.isLocked() ? "closed" : "open";
        }
        return null;
    }

    //Método que devuelve una lista de objeto JSON con las monedas de una habitación
    private static List<JSONObject> getCoinList(List<Item> itemList){
        List<JSONObject> coinList = new ArrayList<>();
        //Se recorre la lista de items y si es una moneda, se crea un objeto JSON con la información y se añade a lista
        for (Item e : itemList) {
            if(e.getClass().getSimpleName().equals("Coin")){
                Coin coin = (Coin)e;
                JSONObject coinJson = new JSONObject();
                coinJson.put("name",coin.getName());
                coinJson.put("x",coin.getCoorX());
                coinJson.put("y",coin.getCoorY());
                coinJson.put("isCollected",coin.isCollected());
                coinList.add(coinJson);
            }
        }
        return coinList;
    }

    //Método que devuelve una lista de objeto JSON con las llaves de una habitación
    private static List<JSONObject> getKeyList(List<Item> itemList){
        List<JSONObject> keyList = new ArrayList<>();
        //Se recorre la lista de items y si es una llave, se crea un objeto JSON con la información y se añade a lista
        for (Item e : itemList) {
            if(e.getClass().getSimpleName().equals("Key")){
                Key key = (Key)e;
                JSONObject keyJson = new JSONObject();
                keyJson.put("name",key.getName());
                keyJson.put("x",key.getCoorX());
                keyJson.put("y",key.getCoorY());
                keyJson.put("cost",key.getCost());
                keyJson.put("isCollected",key.isCollected());
                keyList.add(keyJson);
            }
        }
        return keyList;
    }
}
