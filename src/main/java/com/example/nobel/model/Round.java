package com.example.nobel.model;

import com.example.nobel.model.enums.Move;
import com.example.nobel.model.enums.Result;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_move")
    private Move playerMove;
    @Column(name = "ai_move")
    private Move aiMove;
    @Column(name = "player_result")
    private Result playerResult;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    @JsonIgnore
    private Game game;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", playerMove=" + playerMove +
                ", aiMove=" + aiMove +
                ", playerResult=" + playerResult +
                ", createdAt=" + createdAt +
                '}';
    }
}