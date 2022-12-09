package com.liceu.maze.model;
public class StandardMazeBuilder implements MazeBuilder{
    Maze maze = new Maze();

    @Override
    public void buildRoom(int roomID) {
        //Crea una habitación y asigna un muro en sus cuatro lados
        Room room = new Room(roomID);
        room.setSide(Maze.Directions.NORTH, new Wall());
        room.setSide(Maze.Directions.SOUTH, new Wall());
        room.setSide(Maze.Directions.WEST, new Wall());
        room.setSide(Maze.Directions.EAST, new Wall());
        //Añade la habitación al laberinto
        maze.addRoom(roomID, room);
    }

    @Override
    public void setTarget(int roomID) {
        //Determina que una habitación del laberinto sea la salida (objetivo)
        this.maze.getRoom(roomID).setTarget(true);
    }

    @Override
    public void buildDoor(int roomFrom, int roomTo, Maze.Directions dir) {
        //Crea una puerta abierta
        buildDoorInternal(roomFrom, roomTo, dir);

    }
    private Door buildDoorInternal(int roomFrom, int roomTo, Maze.Directions dir) {
        //Busca las dos habitaciones del laberinto que se recibe por parámetro y se les asigna a una puerta
        Room room1 = maze.getRoom(roomFrom);
        Room room2 = maze.getRoom(roomTo);
        Door door = new Door(room1, room2);
        //Establece el MapSite de una de las habitaciones según la dirección dónde se quiere situar la puerta
        room1.setSide(dir, door);
        //Establece el MapSite de la otra habitación en sentido contrario
        room2.setSide(getOppositeSide(dir), door);
        return door;
    }

    //Método que devuelve el lado contrario de la dirección (W, E, N, S) que se le pase por parámetro
    private Maze.Directions getOppositeSide(Maze.Directions dir) {
        switch(dir) {
            case NORTH: return Maze.Directions.SOUTH;
            case SOUTH: return Maze.Directions.NORTH;
            case WEST: return Maze.Directions.EAST;
            case EAST: return Maze.Directions.WEST;
        }
        throw new RuntimeException("Unrecognized direction");
    }

    @Override
    public void buildDoor(int roomFrom, int roomTo, Maze.Directions dir, Key key) {
        //Crea una puerta, se le asigna una llave y se cierra
        Door d = buildDoorInternal(roomFrom, roomTo, dir);
        d.setKey(key);
        d.close();
    }

    @Override
    public void putKeyInRoom(int roomID, Key key) {
        //Se añade la llave a la lista de items de la habitación correspondiente
        maze.getRoom(roomID).addItem(key);
    }

    @Override
    public void putCoinInRoom(int roomID, Coin coin) {
        //Se añade la moneda a la lista de items de la habitación correspondiente
        maze.getRoom(roomID).addItem(coin);
    }

    @Override
    public Maze getMaze() {
        return this.maze;
    }
}
