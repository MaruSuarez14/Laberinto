package com.liceu.maze.controllers;

import com.liceu.maze.DAO.MySQL.WinnersDAO;
import com.liceu.maze.DAO.MySQL.WinnersDAOMySQL;
import com.liceu.maze.model.Game;
import com.liceu.maze.model.Winner;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/winners")
public class WinnersController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WinnersDAO winnersDAO = new WinnersDAOMySQL();
        List<Winner> list = new ArrayList<>();
        try {
            list = winnersDAO.getWinners();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("winners", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/record.jsp");
        dispatcher.forward(req, resp);
    }
}
