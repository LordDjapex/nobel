package com.example.nobel.controller;

import com.example.nobel.model.Game;
import com.example.nobel.model.Round;
import com.example.nobel.model.dto.GameStatistics;
import com.example.nobel.model.enums.Move;
import com.example.nobel.service.GameService;
import com.example.nobel.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private final GameService gameService;
    @Autowired
    private final StatisticsService statisticsService;

    public GameController(GameService gameService, StatisticsService statisticsService) {
        this.gameService = gameService;
        this.statisticsService = statisticsService;
    }

    @PostMapping("/start")
    public ResponseEntity<Game> startGame(@RequestParam String name) {
        return new ResponseEntity<>(gameService.startNewGame(name), HttpStatus.CREATED);
    }

    @PostMapping("/{gameId}/move")
    public ResponseEntity<Round> makeMove(@PathVariable Long gameId, @RequestBody Move playerMove) {
        return new ResponseEntity<>(gameService.processMove(gameId, playerMove), HttpStatus.OK);
    }

    @PostMapping("/{gameId}/end")
    public ResponseEntity<GameStatistics> endGame(@PathVariable Long gameId) {
        GameStatistics result = gameService.endGame(gameId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
