
import com.example.tennis_scoreboard.service.match_score_calculation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MatchScoreTest {

    MatchScore matchScore;

    @BeforeEach
    void setUp() {
//          matchScore = MatchScore.getInstance();
        matchScore = new MatchScore();
    }


    @Test
    void twoSetsShouldWinMatch() {
// Player1 wins first set(six games of four points)
        for (int i = 0; i < 23; i++) {
            matchScore.winPoint(0);
        }
        assertEquals(State.ONGOING, matchScore.winPoint(0));

// Player1 wins second set(six games of four points) and wins match
        for (int i = 0; i < 23; i++) {
            matchScore.winPoint(0);
        }
        assertEquals(State.PLAYER1_WON, matchScore.winPoint(0));
    }


    @Test
    void winningTieBrakeShouldWinMatch() {
// Player1 wins first set( six games our points)
        for (int i = 0; i < 24; i++) {
            matchScore.winPoint(0);
        }

// Player2 wins second set( six games our points),match ongoing
        for (int i = 0; i < 23; i++) {
            matchScore.winPoint(1);
        }
        assertEquals(State.ONGOING, matchScore.winPoint(1));

// Player1 wins five games
        for (int i = 0; i < 20; i++) {
            matchScore.winPoint(0);
        }

// Player2 wins six games, set ongoing
        for (int i = 0; i < 23; i++) {
            matchScore.winPoint(1);
        }
        assertEquals(State.ONGOING, matchScore.winPoint(1));

//Player1 wins sixth game, tie-brake is played, set ongoing
        for (int i = 0; i < 3; i++) {
            matchScore.winPoint(0);
        }
        assertEquals(State.ONGOING, matchScore.winPoint(0));

// Player2 wins tie-brake if he takes seven points and wins match
        for (int i = 0; i < 6; i++) {
            matchScore.winPoint(1);
        }
        assertEquals(State.PLAYER2_WON, matchScore.winPoint(1));
    }
}
