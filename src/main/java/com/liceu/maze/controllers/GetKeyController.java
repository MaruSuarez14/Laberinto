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

@WebServlet("/getKey")
public class GetKeyController extends HttpServlet {
    GameService gameService = new GameService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game g = (Game) req.getSession().getAttribute("game");
        gameService.setGame(g);
        String name = req.getParameter("name");
        JSONObject json = gameService.getItem(name);
        String jsonString = json.toJSONString();
        req.setAttribute("json", jsonString);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/game.jsp");
        dispatcher.forward(req, resp);
    }
}
