package com.example.tennis_scoreboard.servlet;

import com.example.tennis_scoreboard.dao.MatchDao;
import com.example.tennis_scoreboard.dto.MatchResponseDto;
import com.example.tennis_scoreboard.entity.Match;
import com.example.tennis_scoreboard.utils.MappingUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private final MatchDao matchDao = new MatchDao();
    private static final int PAGE_SIZE = 3;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filterName = req.getParameter("filter_by_player_name");
        String pageParam = req.getParameter("page");
        int pageNumber = 1;

        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                pageNumber = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                log.error(String.format("Page parameter parsing error: %s. Default value is used: {1}", pageParam));
            }
        }
        List<Match> allMatches = matchDao.findAllWithPagination(filterName, PAGE_SIZE, pageNumber);
        int totalMatches = matchDao.countMatches(filterName);

        List<MatchResponseDto> matches = allMatches
                .stream()
                .map(MappingUtils::convertToDto)
                .collect(Collectors.toList());

        req.setAttribute("matches", matches);
        req.setAttribute("currentPage", pageNumber);
        req.setAttribute("totalPages", (int) Math.ceil((double) totalMatches / PAGE_SIZE));

        req.getRequestDispatcher("completed-matches.jsp").forward(req, resp);
    }
}
