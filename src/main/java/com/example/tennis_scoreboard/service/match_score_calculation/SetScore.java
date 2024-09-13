package com.example.tennis_scoreboard.service.match_score_calculation;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class SetScore extends Score<Integer> {
    private CurrentScore<?> currentScore;


    public SetScore() {
        this.currentScore = new GameScore();
    }


    @Override
    public Integer getZeroPoint() {
        return 0;
    }


    @Override
    public State winPoint(int playerNumber) {
        log.info("Player {} is winning a point", playerNumber);
        State state = currentScore.winPoint(playerNumber);

        if (state.equals(State.PLAYER1_WON)) {
            log.info("Player 1 won the game");

            return winSet(playerNumber);

        } else if (state.equals(State.PLAYER2_WON)) {
            log.info("Player 2 won the game");

            return winSet(playerNumber);
        }
        return State.ONGOING;
    }


    public State winSet(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);
        this.currentScore = new GameScore();

        if ((getPlayerScore(playerNumber) >= MAX_POINTS_TO_WIN) &&
                (getPlayerScore(playerNumber) - getOpponentScore(playerNumber) >= POINTS_ADVANTAGE_TO_WIN)) {

            if (playerNumber == 0) {
                log.info("Player 1 won the set");

                return State.PLAYER1_WON;
            }
            if (playerNumber == 1) {
                log.info("Player 2 won the set");

                return State.PLAYER2_WON;
            }
        }

        if ((getPlayerScore(playerNumber).equals(MAX_POINTS_TO_WIN)) &&
                (getOpponentScore(playerNumber).equals(MAX_POINTS_TO_WIN))) {

            log.info("Tie-break has started");
            this.currentScore = new TieBrakeScore();

            return State.ONGOING;
        }

        if ((getPlayerScore(playerNumber) > MAX_POINTS_TO_WIN) &&
                getOpponentScore(playerNumber) == MAX_POINTS_TO_WIN) {

            if (playerNumber == 0) {
                log.info("Player 1  won the set");

                return State.PLAYER1_WON;
            }
            if (playerNumber == 1) {
                log.info("Player 2 won the set");

                return State.PLAYER2_WON;
            }
        }
        log.info("A new game has started");
        this.currentScore = new GameScore();

        return State.ONGOING;
    }
}
