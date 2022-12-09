package com.liceu.maze.controllers;

import com.liceu.maze.model.Game;
import com.liceu.maze.services.GameService;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/open")
public class OpenController extends HttpServlet {
   GameService gameService = new GameService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game g = (Game) req.getSession().getAttribute("game");
        gameService.setGame(g);
        String dir = req.getParameter("dir");
        JSONObject json = gameService.open(dir);
        String jsonString = json.toJSONString();
        req.setAttribute("json", jsonString);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/game.jsp");
        dispatcher.forward(req, resp);
    }
}