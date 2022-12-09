package com.liceu.maze.controllers;

import com.liceu.maze.DAO.MySQL.WinnersDAO;
import com.liceu.maze.DAO.MySQL.WinnersDAOMySQL;
import com.liceu.maze.model.Game;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/endform")
public class EndFormController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Game game = (Game) session.getAttribute("game");
        String name = req.getParameter("name");
        String mazeName = game.getMaze().getMazeID();
        int elapsed_time = game.getElapsedTime();
         WinnersDAO winnersDAO = new WinnersDAOMySQL();
        try {
            winnersDAO.createWinner(name,mazeName,elapsed_time);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/winners");
    }
}
