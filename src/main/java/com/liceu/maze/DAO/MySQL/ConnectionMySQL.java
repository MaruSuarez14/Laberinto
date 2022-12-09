package com.liceu.maze.DAO.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
    private static Connection con;

    public  Connection setConnection () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://mysql/Maze", "root", "root");
        } catch (SQLException | ClassNotFoundException sqlEx) {
            System.err.println("Error creando la conexión");
            System.err.println(sqlEx);
        }
        return con;

    }

}
