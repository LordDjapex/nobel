package com.example.nobel.service;

import com.example.nobel.model.Game;
import com.example.nobel.model.Round;
import com.example.nobel.model.dto.GameStatistics;
import com.example.nobel.model.enums.Move;
import com.example.nobel.model.enums.Result;
import com.example.nobel.repository.GameRepository;
import com.example.nobel.repository.RoundRepository;
import com.example.nobel.util.AI;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    RoundRepository roundRepository;

    @Transactional
    public Game startNewGame(String name) {
        Game game = new Game();
        game.setActive(true);
        game.setName(name);
        game.setCreatedAt(LocalDateTime.now());
        gameRepository.save(game);
        return game;
    }

    @Transactional
    public GameStatistics endGame(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found!"));
        game.setActive(false);
        game = gameRepository.save(game);
        return gameRepository.getGameStatistics(game.getId());
    }

    @Transactional
    public Round processMove(Long gameId, Move playerMove) {
        Game game = gameRepository.findById(gameId).orElseThrow(() ->
                            new RuntimeException("Game not found!"));

        if (!game.isActive()) {
            throw new IllegalStateException("Game with ID " + gameId + " is already terminated.");
        }

        Move aiMove = AI.predictMove(game);
        Result result = getResult(playerMove, aiMove);

        game.setUpdatedAt(java.time.LocalDateTime.now());
        gameRepository.save(game);

        Round roundResult = new Round();
        roundResult.setGame(game);
        roundResult.setPlayerMove(playerMove);
        roundResult.setAiMove(aiMove);
        roundResult.setPlayerResult(result);
        roundResult.setCreatedAt(LocalDateTime.now());
        roundRepository.save(roundResult);

        return roundResult;
    }

    private Result getResult(Move playerMove, Move aiMove) {
        if (playerMove == aiMove)
            return Result.DRAW;
        if(isPlayerWin(playerMove, aiMove))
            return Result.WIN;

        return Result.LOSS;
    }

    private boolean isPlayerWin(Move playerMove, Move aiMove) {
        return (playerMove == Move.ROCK && aiMove == Move.SCISSORS) ||
                (playerMove == Move.PAPER && aiMove == Move.ROCK) ||
                (playerMove == Move.SCISSORS && aiMove == Move.PAPER);
    }
}
