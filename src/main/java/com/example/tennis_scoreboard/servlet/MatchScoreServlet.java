package com.example.tennis_scoreboard.servlet;

import com.example.tennis_scoreboard.dto.MatchRequestDto;
import com.example.tennis_scoreboard.dto.MatchResponseDto;
import com.example.tennis_scoreboard.service.FinishedMatchesPersistenceService;
import com.example.tennis_scoreboard.service.OngoingMatchesService;
import com.example.tennis_scoreboard.service.match_score_calculation.State;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;


@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatch = OngoingMatchesService.getInstance();
    private final FinishedMatchesPersistenceService finishedMatch = new FinishedMatchesPersistenceService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matchId = req.getParameter("uuid");
        UUID uuid = UUID.fromString(matchId);

        MatchRequestDto match = ongoingMatch.findById(uuid);

        req.setAttribute("match", match);
        req.setAttribute("uuid", uuid);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matchId = req.getParameter("uuid");
        UUID uuid = UUID.fromString(matchId);
        String winner = req.getParameter("winner");

        MatchRequestDto match = ongoingMatch.findById(uuid);

        MatchResponseDto responseDto = new MatchResponseDto();
        responseDto.setPlayer1(match.getPlayer1());
        responseDto.setPlayer2(match.getPlayer2());

        if (winner.equals("playerOne")) {

            if (match.getScore().winPoint(0) == State.PLAYER1_WON) {
                responseDto.setWinner(match.getPlayer1());

                finishedMatch.add(responseDto);
                ongoingMatch.remove(uuid);

                req.setAttribute("match", responseDto);
                req.getRequestDispatcher("/finish-match.jsp").forward(req, resp);
            }
        } else if (winner.equals("playerTwo")) {

            if (match.getScore().winPoint(1) == State.PLAYER2_WON) {
                responseDto.setWinner(match.getPlayer2());

                finishedMatch.add(responseDto);
                ongoingMatch.remove(uuid);

                req.setAttribute("match", responseDto);
                req.getRequestDispatcher("/finish-match.jsp").forward(req, resp);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuid);
    }
}
