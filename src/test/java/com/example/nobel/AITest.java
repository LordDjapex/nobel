package com.example.nobel;

import com.example.nobel.model.Game;
import com.example.nobel.model.Round;
import com.example.nobel.model.enums.Move;
import com.example.nobel.util.AI;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class AITest {

    @Test
    public void testPredictMoveWhenNoRounds() {
        Game game = new Game();
        game.setRounds(Collections.emptyList()); // No rounds in the game

        Move move = AI.predictMove(game);

        assertNotNull(move, "The predicted move should not be null.");
        assertTrue(Arrays.asList(Move.values()).contains(move), "The predicted move should be one of the valid moves.");
    }


    @Test
    public void testPredictMoveWithFrequentMove() {
        Round round1 = new Round();
        round1.setPlayerMove(Move.ROCK);
        round1.setAiMove(Move.SCISSORS);

        Round round2 = new Round();
        round2.setPlayerMove(Move.ROCK);
        round2.setAiMove(Move.SCISSORS);

        Round round3 = new Round();
        round3.setPlayerMove(Move.ROCK);
        round3.setAiMove(Move.PAPER);

        Game game = new Game();
        game.setRounds(Arrays.asList(round1, round2, round3));

        Move move = AI.predictMove(game);

        assertNotNull(move, "The predicted move should not be null.");
        assertEquals(Move.PAPER, move);
    }

}
