package com.example.nobel.model.dto;

public interface GameStatistics {

    String getGameName();
    Long getGameId();
    Long getRoundsPlayed();
    Long getPlayerWins();
    Long getAiWins();
    Long getDraws();
}
