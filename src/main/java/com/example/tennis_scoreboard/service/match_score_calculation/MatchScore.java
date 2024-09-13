package com.example.tennis_scoreboard.service.match_score_calculation;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class MatchScore extends Score<Integer> {
    private SetScore setScore;


    public MatchScore() {
        this.setScore = new SetScore();
    }


    @Override
    protected Integer getZeroPoint() {
        return 0;
    }


    @Override
    public State winPoint(int playerNumber) {
        State state = setScore.winPoint(playerNumber);

        if (state.equals(State.PLAYER1_WON)) {
            log.info("Player 1 won the set");

            return winMatch(playerNumber);
        } else if (state.equals(State.PLAYER2_WON)) {
            log.info("Player 2 won the set");

            return winMatch(playerNumber);
        }
        return State.ONGOING;
    }


    public State winMatch(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);

        if (getPlayerScore(playerNumber) == POINTS_ADVANTAGE_TO_WIN) {
            if (playerNumber == 0) {
                log.info("Player 1 won the match");

                return State.PLAYER1_WON;
            }
            if (playerNumber == 1) {
                log.info("Player 2 won the match");

                return State.PLAYER2_WON;
            }
        }
        log.info("A new set has started");
        this.setScore = new SetScore();

        return State.ONGOING;
    }
}
