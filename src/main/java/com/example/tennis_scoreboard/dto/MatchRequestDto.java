package com.example.tennis_scoreboard.dto;


import com.example.tennis_scoreboard.service.match_score_calculation.MatchScore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchRequestDto {

    private UUID id = UUID.randomUUID();

    private PlayerRequestDto player1;

    private PlayerRequestDto player2;

    private MatchScore score = new MatchScore();
}
