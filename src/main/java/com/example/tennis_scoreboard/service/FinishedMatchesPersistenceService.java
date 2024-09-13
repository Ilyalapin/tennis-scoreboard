package com.example.tennis_scoreboard.service;

import com.example.tennis_scoreboard.dao.MatchDao;
import com.example.tennis_scoreboard.dao.PlayerDao;
import com.example.tennis_scoreboard.dto.MatchResponseDto;
import com.example.tennis_scoreboard.entity.Match;
import com.example.tennis_scoreboard.entity.Player;
import com.example.tennis_scoreboard.utils.MappingUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Getter
public class FinishedMatchesPersistenceService {

    private final PlayerDao playerDao = new PlayerDao();
    private final MatchDao matchDao = new MatchDao();
    private final OngoingMatchesService ongoingMatches = OngoingMatchesService.getInstance();


    public synchronized void add(MatchResponseDto matchResponseDto) {
        Player player1 = null;
        Player player2 = null;

        try {
            log.info("Start persist first player -> {}", matchResponseDto.getPlayer1());
            player1 = playerDao.add(MappingUtils.convertToEntity(matchResponseDto.getPlayer1()));
        } catch (Exception e) {
            Optional<Player> player = playerDao.findByName(matchResponseDto.getPlayer1().getName());
            if (player.isPresent()) {
                player1 = player.get();
            }
        }
        log.info("Successful create first player -> {}", player1 != null ? player1.getName() : null);

        try {
            log.info("Start persist second player -> {}", matchResponseDto.getPlayer2());
            player2 = playerDao.add(MappingUtils.convertToEntity(matchResponseDto.getPlayer2()));
        } catch (Exception e) {
            Optional<Player> player = playerDao.findByName(matchResponseDto.getPlayer2().getName());
            if (player.isPresent()) {
                player2 = player.get();
            }
        }
        log.info("Successful create second player -> {}", player2 != null ? player2.getName() : null);

        Player winner;
        if (player1 != null && matchResponseDto.getWinner().getName().equalsIgnoreCase(player1.getName())) {
            winner = player1;
        } else {
            winner = player2;
        }
        log.info("Successful create winner -> {}", winner != null ? winner.getName() : null);
        matchDao.add(new Match(player1, player2, winner));
    }
}
