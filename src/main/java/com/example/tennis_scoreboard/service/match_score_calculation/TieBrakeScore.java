package com.example.tennis_scoreboard.service.match_score_calculation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TieBrakeScore extends CurrentScore<Integer> {

    @Override
    protected Integer getZeroPoint() {
        return 0;
    }


    @Override
    public State winPoint(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);

        if ((getPlayerScore(playerNumber) > MAX_POINTS_TO_WIN) &&
                (getPlayerScore(playerNumber) - getOpponentScore(playerNumber) >= POINTS_ADVANTAGE_TO_WIN)) {

            if (playerNumber == 0) {
                log.info("Player 1 won the tie brake");

                return State.PLAYER1_WON;
            }
            if (playerNumber == 1) {
                log.info("Player 2 won the tie brake");

                return State.PLAYER2_WON;
            }
        }
        return State.ONGOING;
    }
}
