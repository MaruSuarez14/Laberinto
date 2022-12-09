package com.liceu.maze.DAO.MySQL;

import com.liceu.maze.model.Game;
import com.liceu.maze.model.Winner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WinnersDAOMySQL implements WinnersDAO {
    private ConnectionMySQL connectionMySQL = new ConnectionMySQL();
    private Connection con = connectionMySQL.setConnection();

    @Override
    public boolean createWinner(String name, String mazeName, int elapsed_time) throws SQLException {
        try {
            PreparedStatement insertWinner = con.prepareStatement("INSERT INTO winners (Name, Maze, Elapsed_Time) VALUES (?, ?, ?)");
            insertWinner.setString(1, name);
            insertWinner.setString(2, mazeName);
            insertWinner.setInt(3, elapsed_time);
            int result = insertWinner.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Winner> getWinners() throws SQLException {
        List<Winner> list = new ArrayList<>();
        try {
            PreparedStatement getWinnerStmnt = con.prepareStatement("SELECT * FROM winners ORDER BY Elapsed_Time");
            var result = getWinnerStmnt.executeQuery();
            while (result.next()) {
                String playerName = result.getString("Name");
                String mazeName = result.getString("Maze");
                int elapsed_time = result.getInt("Elapsed_Time");
                Winner winner = new Winner(playerName, mazeName, elapsed_time);
                list.add(winner);
            }
        } catch (SQLException e) {

        }
        return list;
    }

}
