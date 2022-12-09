package com.liceu.maze.filters;

import com.liceu.maze.model.Game;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = { "/nav", "/getCoin", "/getKey", "/open" })
public class GameFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Game game = (Game) req.getSession().getAttribute("game");
        if(game==null) {
            res.sendRedirect("/start");
            return;
        }
        chain.doFilter(req, res);
    }
}
