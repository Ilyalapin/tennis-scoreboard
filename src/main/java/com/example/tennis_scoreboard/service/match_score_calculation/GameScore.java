package com.example.tennis_scoreboard.service.match_score_calculation;


public class GameScore extends CurrentScore<GamePoint> {

    @Override
    public GamePoint getZeroPoint() {
        return GamePoint.LOVE;
    }


    @Override
    public State winPoint(int playerNumber) {

        GamePoint playerScore = getPlayerScore(playerNumber);
        GamePoint opponentScore = getOpponentScore(playerNumber);

        if (playerScore.ordinal() <= GamePoint.THIRTY.ordinal()) {
            setPlayerScore(playerNumber, playerScore.increment());

            return State.ONGOING;
        }

        if (playerScore.equals(GamePoint.FOURTY)) {
            getOpponentScore(playerNumber);

            if (opponentScore.equals(GamePoint.FOURTY)) {
                setPlayerScore(playerNumber, GamePoint.ADVANTAGE);
            }
            if (opponentScore.equals(GamePoint.ADVANTAGE)) {
                setOpponentScore(playerNumber, GamePoint.FOURTY);
            }
            if (opponentScore.ordinal() <= POINTS_ADVANTAGE_TO_WIN) {

                if (playerNumber == 0) {
                    return State.PLAYER1_WON;
                }
                if (playerNumber == 1) {
                    return State.PLAYER2_WON;
                }
            }
        }

        if (playerScore.equals(GamePoint.ADVANTAGE) ||
                (playerScore.ordinal() - opponentScore.ordinal() >= POINTS_ADVANTAGE_TO_WIN)) {

            if (playerNumber == 0) {
                return State.PLAYER1_WON;
            }
            if (playerNumber == 1) {
                return State.PLAYER2_WON;
            }
        }
        return State.ONGOING;
    }
}





