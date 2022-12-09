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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/reset")
public class ResetController extends HttpServlet {
    GameService gameService = new GameService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Game game = (Game) session.getAttribute("game");
        JSONObject json = gameService.start(game.getMaze().getMazeID());
        session.setAttribute("game", gameService.getGame());
        String jsonString = json.toJSONString();
        req.setAttribute("json", jsonString);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/game.jsp");
        dispatcher.forward(req, resp);
    }
}
