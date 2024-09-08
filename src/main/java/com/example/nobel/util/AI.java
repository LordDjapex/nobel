package com.example.nobel.util;

import com.example.nobel.model.Game;
import com.example.nobel.model.Round;
import com.example.nobel.model.enums.Move;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AI {
    public static Move predictMove(Game game) {

            List<Round> rounds = game.getRounds();
            if (rounds.isEmpty()) {
                return getRandomMove();
            }

            Map<Move, Integer> moveFrequency = new HashMap<>();
            for (Move move : Move.values()) {
                moveFrequency.put(move, 0);
            }

            List<Move> playerMoves = rounds.stream().map(Round::getPlayerMove).toList();

            for (Move move : playerMoves) {
                moveFrequency.put(move, moveFrequency.get(move) + 1);
            }

            Move mostFrequentMove = null;
            int maxCount = 0;
            for (Map.Entry<Move, Integer> entry : moveFrequency.entrySet()) {
                if (entry.getValue() > maxCount + 2) {
                    mostFrequentMove = entry.getKey();
                    maxCount = entry.getValue();
                }
            }

            if (mostFrequentMove != null) {
                switch (mostFrequentMove) {
                    case ROCK:
                        return Move.PAPER;
                    case PAPER:
                        return Move.SCISSORS;
                    case SCISSORS:
                        return Move.ROCK;
                    default:
                        return getRandomMove();
                }
            } else {
                return getRandomMove();
            }
        }

    private static Move getRandomMove() {
        Move[] moves = Move.values();
        int idx = (int) (Math.random() * moves.length);
        return moves[idx];
    }
}
