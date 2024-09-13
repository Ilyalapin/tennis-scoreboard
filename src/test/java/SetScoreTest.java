import com.example.tennis_scoreboard.service.match_score_calculation.State;
import com.example.tennis_scoreboard.service.match_score_calculation.SetScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SetScoreTest {

    private SetScore score;


    @BeforeEach
    void setUp() {
        score = new SetScore();
    }

    @Test
    void TwentyFourPointsShouldWinSet() {
        for (int i = 0; i < 23; i++) {
            score.winPoint(0);
        }
        assertEquals(State.PLAYER1_WON, score.winPoint(0));
    }

    @Test
    void setShouldContinueWithTheScoreSevenBySix() {
// Player1 wins five games
        for (int i = 0; i < 20; i++) {
            score.winPoint(0);
        }

// Player2 wins six games
        score.setPlayerScore(1, 6);

// Player1 wins two games
        for (int i = 0; i < 7; i++) {
            score.winPoint(0);
        }
// Score 7 to 6, set ongoing
        assertEquals(State.ONGOING, score.winPoint(0));
    }

    @Test
    void shouldBeTieBrakeIfBothPlayersHaveSixPoints() {
        score.setPlayerScore(0, 6);
        for (int i = 0; i < 23; i++) {
            score.winPoint(1);
        }
        assertEquals(State.ONGOING, score.winPoint(0));

// Score 7 to 6, tie-brake ongoing
        score.setPlayerScore(0, 6);
        for (int i = 0; i < 7; i++) {
            score.winPoint(1);
        }
        assertEquals(State.ONGOING, score.winPoint(0));

// Player2 wins tie-brake if he takes one more point
        assertEquals(State.PLAYER2_WON, score.winPoint(1));
    }
}
