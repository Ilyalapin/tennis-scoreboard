package com.example.tennis_scoreboard.utils;

import com.example.tennis_scoreboard.dto.MatchRequestDto;
import com.example.tennis_scoreboard.dto.PlayerRequestDto;
import com.example.tennis_scoreboard.exception.InvalidParameterException;


public class ValidationUtils {

    public static void validatePlayerName(String name) {
        if (name == null) {
            throw new InvalidParameterException("Missing parameter - name");
        }
        if (!name.matches("[\\p{L} .]+")) {
            throw new InvalidParameterException("Invalid parameter '" + name + "' ,player's name must contain only letters");
        }
        if (name.length() < 3) {
            throw new InvalidParameterException("Invalid parameter '" + name + "' ,player's name must contain at least three letters");
        }
    }


    public static void validateMatch(MatchRequestDto matchRequestDto) {
        PlayerRequestDto player1 = matchRequestDto.getPlayer1();
        PlayerRequestDto player2 = matchRequestDto.getPlayer2();

        if (player1 == null) {
            throw new InvalidParameterException("Missing parameter - player1");
        }
        if (player2 == null) {
            throw new InvalidParameterException("Missing parameter - player2");
        }
        if (player1.equals(player2)) {
            throw new InvalidParameterException("player name's cannot be the same or empty");
        }

        validatePlayerName(player1.getName());
        validatePlayerName(player2.getName());
    }
}
