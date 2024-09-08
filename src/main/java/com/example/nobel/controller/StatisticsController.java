package com.example.nobel.controller;

import com.example.nobel.model.dto.GameStatistics;
import com.example.nobel.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/{gameId}")
    public ResponseEntity<GameStatistics> getStatistics(@PathVariable Long gameId) {
        return new ResponseEntity<>(statisticsService.getStatistics(gameId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GameStatistics>> getStatisticsList() {
        return new ResponseEntity<>(statisticsService.getStatisticsList(), HttpStatus.OK);
    }
}
