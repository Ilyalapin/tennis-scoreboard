package com.example.tennis_scoreboard.servlet;

import java.io.*;


import com.example.tennis_scoreboard.dto.MatchRequestDto;
import com.example.tennis_scoreboard.dto.PlayerRequestDto;
import com.example.tennis_scoreboard.exception.InvalidParameterException;
import com.example.tennis_scoreboard.service.OngoingMatchesService;
import com.example.tennis_scoreboard.utils.ValidationUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        request.getRequestDispatcher("new-match.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        MatchRequestDto matchRequestDto = new MatchRequestDto();

        matchRequestDto.setPlayer1(new PlayerRequestDto(req.getParameter("playerOne")));
        matchRequestDto.setPlayer2(new PlayerRequestDto(req.getParameter("playerTwo")));

        try {
            ValidationUtils.validateMatch(matchRequestDto);

            ongoingMatchesService.add(matchRequestDto);
            resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + matchRequestDto.getId());
        } catch (InvalidParameterException exception) {
            req.setAttribute("error", exception.getMessage());
            req.getRequestDispatcher("new-match.jsp").forward(req, resp);
        }

    }
}
