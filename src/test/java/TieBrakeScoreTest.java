import com.example.tennis_scoreboard.service.match_score_calculation.State;
import com.example.tennis_scoreboard.service.match_score_calculation.TieBrakeScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TieBrakeScoreTest {

    private TieBrakeScore score;

    @BeforeEach
    public void setUp() {
        score = new TieBrakeScore();
    }


    @Test
    void sevenPointsShouldWinTieBrake() {
        for (int i = 0; i < 6; i++) {
            score.winPoint(0);
        }
        assertEquals(State.PLAYER1_WON, score.winPoint(0));
    }


    @Test
    void ShouldWinTieBrakeByTwoPoints() {

        for (int i = 0; i < 6; i++) {
            score.winPoint(0);
            score.winPoint(1);
        }
        assertEquals(State.ONGOING, score.winPoint(1));
        assertEquals(State.PLAYER2_WON, score.winPoint(1));
    }
}
