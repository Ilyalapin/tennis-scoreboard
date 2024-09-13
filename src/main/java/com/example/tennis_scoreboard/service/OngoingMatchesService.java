package com.example.tennis_scoreboard.service;


import com.example.tennis_scoreboard.dto.MatchRequestDto;
import com.example.tennis_scoreboard.dto.PlayerRequestDto;
import com.example.tennis_scoreboard.exception.NotFoundException;
import com.example.tennis_scoreboard.service.match_score_calculation.MatchScore;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Getter
public class OngoingMatchesService {

    private static OngoingMatchesService instance;
    private final Map<UUID, MatchRequestDto> currentMatches = new ConcurrentHashMap<>();
    private final PlayerRequestDto player1;
    private final PlayerRequestDto player2;
    private final MatchScore score;


    private OngoingMatchesService() {
        this.player1 = new PlayerRequestDto();
        this.player2 = new PlayerRequestDto();
        this.score = new MatchScore();
    }


    public static synchronized OngoingMatchesService getInstance() {
        if (instance == null) {
            instance = new OngoingMatchesService();
        }
        return instance;
    }


    public synchronized UUID add(MatchRequestDto matchRequestDto) {
        UUID uuid = matchRequestDto.getId();
        currentMatches.put(uuid, matchRequestDto);
        log.info("Successful create match -> {}", matchRequestDto);

        return uuid;
    }


    public synchronized MatchRequestDto findById(UUID uuid) {
        if (currentMatches.containsKey(uuid)) {
            return currentMatches.getOrDefault(uuid, null);
        } else {
            return null;
        }
    }


    public synchronized void remove(UUID uuid) {
        if (currentMatches.containsKey(uuid)) {
            currentMatches.remove(uuid);
            log.info("Successful remove match from ongoing matches");
        } else {
            throw new NotFoundException("Match with id " + uuid + " not found");
        }
    }
}
