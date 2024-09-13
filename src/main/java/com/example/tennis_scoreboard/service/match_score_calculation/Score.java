package com.example.tennis_scoreboard.service.match_score_calculation;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> {

    protected static final int POINTS_ADVANTAGE_TO_WIN = 2;
    protected static final int MAX_POINTS_TO_WIN = 6;
    private final List<T> playerScores = new ArrayList<>();

    public Score() {
        playerScores.add(getZeroPoint());
        playerScores.add(getZeroPoint());
    }

    public T getPlayerScore(int playerNumber) {
        return playerScores.get(playerNumber);
    }


    public T getOpponentScore(int playerNumber) {
        if (playerNumber == 0) {
            return playerScores.get(1);
        }
        return playerScores.get(0);
    }


    public void setPlayerScore(int playerNumber, T score) {
        playerScores.set(playerNumber, score);
    }


    public void setOpponentScore(int playerNumber, T score) {
        if (playerNumber == 0) {
            playerScores.set(1, score);
        } else {
            playerScores.set(0, score);
        }
    }


    protected abstract T getZeroPoint();

    public abstract State winPoint(int playerNumber);
}
