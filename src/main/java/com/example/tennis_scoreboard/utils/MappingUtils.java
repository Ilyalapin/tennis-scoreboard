package com.example.tennis_scoreboard.utils;


import com.example.tennis_scoreboard.dto.MatchResponseDto;
import com.example.tennis_scoreboard.dto.PlayerRequestDto;
import com.example.tennis_scoreboard.entity.Match;
import com.example.tennis_scoreboard.entity.Player;
import org.modelmapper.ModelMapper;

public class MappingUtils {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();


    public static MatchResponseDto convertToDto(Match match) {
        return MODEL_MAPPER.map(match, MatchResponseDto.class);
    }


    public static Player convertToEntity(PlayerRequestDto playerDto) {
        return MODEL_MAPPER.map(playerDto, Player.class);
    }
}
