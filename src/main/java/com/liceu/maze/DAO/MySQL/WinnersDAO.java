package com.liceu.maze.DAO.MySQL;

import com.liceu.maze.model.Game;
import com.liceu.maze.model.Winner;

import java.sql.SQLException;
import java.util.List;

public interface WinnersDAO {
    boolean createWinner(String name, String mazeName, int elapsed_time) throws SQLException;
    List<Winner> getWinners() throws SQLException;

}
