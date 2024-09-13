
import com.example.tennis_scoreboard.exception.InvalidParameterException;
import com.example.tennis_scoreboard.utils.ValidationUtils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationUtilsTest {

    @Test
    public void shouldGetAnInvalidParameterException() {
        String player1 = "Qwe123";
        String player2 = "";
        String player3 = "Qw";

        assertThrows(InvalidParameterException.class, () -> ValidationUtils.validatePlayerName(player1));
        assertThrows(InvalidParameterException.class, () -> ValidationUtils.validatePlayerName(player2));
        assertThrows(InvalidParameterException.class, () -> ValidationUtils.validatePlayerName(player3));
    }
}
