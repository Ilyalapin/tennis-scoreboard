
import com.example.tennis_scoreboard.service.match_score_calculation.GamePoint;
import com.example.tennis_scoreboard.service.match_score_calculation.GameScore;
import com.example.tennis_scoreboard.service.match_score_calculation.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameScoreTest {
    private GameScore score;

    @BeforeEach
    void setUp() {
        score = new GameScore();
    }

    @Test
    void fourPointsShouldWinGame() {
        for (int i = 0; i < 3; i++) {
            assertEquals(State.ONGOING, score.winPoint(0));
        }
        assertEquals(State.PLAYER1_WON, score.winPoint(0));
    }


    @Test
    void checkAdvantageIfBothPlayersHaveFourtyPoints() {
// AD for player1
        for (int i = 0; i < 4; i++) {
            score.winPoint(0);
            score.winPoint(1);
        }
        score.winPoint(0);

        assertEquals(GamePoint.ADVANTAGE, score.getPlayerScore(0));
        assertEquals(GamePoint.FOURTY, score.getPlayerScore(1));

// AD for player2  if won two times
        for (int i = 0; i < 2; i++) {
            score.winPoint(1);
        }

        assertEquals(GamePoint.FOURTY, score.getPlayerScore(0));
        assertEquals(GamePoint.ADVANTAGE, score.getPlayerScore(1));

// Player2 wins if he takes one more point
        score.winPoint(1);
        assertEquals(State.PLAYER2_WON, score.winPoint(1));
    }
}
