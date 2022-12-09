package com.liceu.maze.model;

public class Game {
    //Laberinto que se usa en el juego
    private Maze maze;
    //Jugador
    private Player player;
    //Mensaje que se le mostrará al usuario con las interacciones que vaya teniendo
    private String message = "";
    //Determina si el juego ha terminado
    private boolean endGame = false;
    //Tiempo total de juego
    private int elapsedTime;

    public Game(Maze maze, Player player) {
        this.maze = maze;
        this.player = player;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
}
