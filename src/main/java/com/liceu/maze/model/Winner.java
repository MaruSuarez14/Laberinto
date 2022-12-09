package com.liceu.maze.model;

public class Winner {
    private String userName;
    private String mazeName;
    private int elapsedTime;

    public Winner(String name, String mazeName, int elapsedTime) {
        this.userName = name;
        this.mazeName = mazeName;
        this.elapsedTime = elapsedTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getMazeName() {
        return mazeName;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }
}
