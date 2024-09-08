package com.example.nobel.service;

import com.example.nobel.model.Game;
import com.example.nobel.model.dto.GameStatistics;
import com.example.nobel.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    GameRepository gameRepository;

    public GameStatistics getStatistics(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found!"));
        return gameRepository.getGameStatistics(game.getId());
    }

    public List<GameStatistics> getStatisticsList() {
        return gameRepository.getGameStatisticsList();
    }
}
