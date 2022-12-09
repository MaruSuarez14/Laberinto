package com.liceu.maze.services;

import com.liceu.maze.model.*;

import java.util.ArrayList;
import java.util.List;

public class GameBuilder {

    //Método para crear un juego (le asigna un jugador y un laberinto)
    public static Game createGame(String mapID) {
        Player player = new Player();
        Maze maze = filterMaze(mapID);
        maze.setMazeID(mapID);
        player.setCurrentRoom(maze.getRoom(1));
        return new Game(maze, player);
    }

    //Método para determinar la versión del laberinto según la ID proporcionada
    public static Maze filterMaze (String mapID) {
        switch (mapID){
            case "RegularMaze":
                return createMazeVersion2();
            case "HardMaze":
                return createMazeVersion3();
            default:
                return createMazeVersion1();
        }
    }

    //Crea las partes en común que tienen todos los laberintos
    public static StandardMazeBuilder createCommonMaze (int roomsN, int coinsN, int roomT) {
        //Se crea la instancia de la clase para construir un laberinto estándar
        StandardMazeBuilder mazeBuilder = new StandardMazeBuilder();

        //Número de habitaciones del laberinto
        int nRooms = roomsN;
        //Número de monedas totales repartidas por el laberinto
        int nCoins = coinsN;
        //Número de la habitación objetivo
        int roomTarget = roomT;

        //Se crean las habitaciones
        for (int i = 1; i < nRooms+1; i++) {
            mazeBuilder.buildRoom(i);
        }

        //Se crean las monedas
        List<Coin> coinList = createCoins(nCoins);

        //Se pone las monedas en las habitaciones correspondientes
        for (Coin c: coinList) {
            int room = roomTarget;
            while (room==roomTarget){
                room = (int) (Math.round(Math.random()*(nRooms-1))+1);
            }
            mazeBuilder.putCoinInRoom(room, c);
        }

        //Se asigna la habitación objetivo para ganar la partida
        mazeBuilder.setTarget(roomTarget);

        return mazeBuilder;
    }

    //Crea el laberinto "Chill Maze"
    public static Maze createMazeVersion1(){
        //Se crean las habitaciones del laberinto y las monedas que tenga, también se asigna el número de la habitación objetivo
        StandardMazeBuilder mazeBuilder = createCommonMaze(6, 6, 4);

        //Se crean las llaves
        Key k1 = new Key(0.7, 0.5,"Key1",5);

        //Se crean las puertas
        mazeBuilder.buildDoor(1,2, Maze.Directions.EAST);
        mazeBuilder.buildDoor(1,4, Maze.Directions.SOUTH, k1);
        mazeBuilder.buildDoor(2,3, Maze.Directions.EAST);
        mazeBuilder.buildDoor(2,5, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(5,6, Maze.Directions.EAST);

        //Se pone la llave en la habitación correspondiente
        mazeBuilder.putKeyInRoom(6, k1);

        return mazeBuilder.getMaze();
    }

    //Crea el laberinto "Regular Maze"
    public static Maze createMazeVersion2(){
        //Se crean las habitaciones del laberinto y las monedas que tenga, también se asigna el número de la habitación objetivo
        StandardMazeBuilder mazeBuilder = createCommonMaze(12, 14, 12);

        //Se crean las llaves
        Key k1 = new Key(0.7, 0.5,"Key1",5);
        Key k2 = new Key(0.2, 0.8,"Key2",8);

        //Se crean las puertas
        mazeBuilder.buildDoor(1,2, Maze.Directions.EAST);
        mazeBuilder.buildDoor(1,8, Maze.Directions.WEST);
        mazeBuilder.buildDoor(8,7, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(2,5, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(3,4, Maze.Directions.SOUTH, k1);
        mazeBuilder.buildDoor(5,4, Maze.Directions.EAST);
        mazeBuilder.buildDoor(5,6, Maze.Directions.WEST);
        mazeBuilder.buildDoor(6,7, Maze.Directions.WEST);
        mazeBuilder.buildDoor(6,10, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(7,9, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(9,10, Maze.Directions.EAST);
        mazeBuilder.buildDoor(10,11, Maze.Directions.EAST);
        mazeBuilder.buildDoor(11,12, Maze.Directions.EAST, k2);

        //Se pone la llave en la habitación correspondiente
        mazeBuilder.putKeyInRoom(7, k1);
        mazeBuilder.putKeyInRoom(3, k2);

        return mazeBuilder.getMaze();
    }

    //Crea el laberinto "Hard Maze"
    public static Maze createMazeVersion3(){
        //Se crean las habitaciones del laberinto y las monedas que tenga, también se asigna el número de la habitación objetivo
        StandardMazeBuilder mazeBuilder = createCommonMaze(20, 22, 19);

        //Se crean las llaves
        Key k1 = new Key(0.7, 0.5,"Key1",5);
        Key k2 = new Key(0.7, 0.8,"Key2",5);
        Key k3 = new Key(0.3, 0.6,"Key3",10);

        //Se crean las puertas
        mazeBuilder.buildDoor(1,2, Maze.Directions.EAST);
        mazeBuilder.buildDoor(2,3, Maze.Directions.EAST);
        mazeBuilder.buildDoor(3,4, Maze.Directions.EAST);
        mazeBuilder.buildDoor(4,5, Maze.Directions.EAST, k2);
        mazeBuilder.buildDoor(1,6, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(2,7, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(4,9, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(5,10, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(6,7, Maze.Directions.EAST);
        mazeBuilder.buildDoor(7,8, Maze.Directions.EAST);
        mazeBuilder.buildDoor(8,9, Maze.Directions.EAST);
        mazeBuilder.buildDoor(8,13, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(10,15, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(11,12, Maze.Directions.EAST);
        mazeBuilder.buildDoor(14,15, Maze.Directions.EAST);
        mazeBuilder.buildDoor(11,16, Maze.Directions.SOUTH, k1);
        mazeBuilder.buildDoor(12,17, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(13,18, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(14,19, Maze.Directions.SOUTH, k3);
        mazeBuilder.buildDoor(15,20, Maze.Directions.SOUTH);
        mazeBuilder.buildDoor(17,18, Maze.Directions.EAST);

        //Se pone la llave en la habitación correspondiente
        mazeBuilder.putKeyInRoom(17, k1);
        mazeBuilder.putKeyInRoom(16, k2);
        mazeBuilder.putKeyInRoom(20, k3);

        return mazeBuilder.getMaze();
    }

    //Método para crear una lista de monedas aleatorias
    public static List<Coin> createCoins(int amount){
        List<Coin> coinList = new ArrayList<>();
        for (int i = 1; i < amount + 1; i++) {
            double random1 = Math.ceil(((Math.random() * 0.9 - 0.1) + 0.1) * 10.0) / 10.0;
            double random2 = Math.ceil(((Math.random() * 0.9 - 0.1) + 0.1) * 10.0) / 10.0;
            Coin c = new Coin("Coin_" + i, random1, random2, false);
            coinList.add(c);
        }
        return coinList;
    }

}
