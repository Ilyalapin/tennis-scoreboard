package com.example.tennis_scoreboard.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponseDto {

    private PlayerRequestDto player1;

    private PlayerRequestDto player2;

    private PlayerRequestDto winner;
}
