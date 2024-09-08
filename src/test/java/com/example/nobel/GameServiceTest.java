package com.example.nobel;

import com.example.nobel.model.Game;
import com.example.nobel.model.dto.GameStatistics;
import com.example.nobel.repository.GameRepository;
import com.example.nobel.service.GameService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameStatistics gameStatistics;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }
    @Test
    public void testStartNewGame() {

        Game game = new Game();
        game.setActive(true);
        game.setName("example");

        when(gameRepository.save(any(Game.class))).thenReturn(game);

        Game result = gameService.startNewGame("example");

        assertNotNull(result, "The created game should not be null.");
        assertTrue(result.isActive(), "The new game should be active.");
        assertEquals(result.getName(), "example");
    }

    @Test
    public void testEndGame() {
        Long gameId = 1L;
        Game game = new Game();
        game.setId(gameId);
        game.setActive(true);

        when(gameRepository.findById(gameId)).thenReturn(java.util.Optional.of(game));
        when(gameRepository.save(any(Game.class))).thenReturn(game);
        when(gameRepository.getGameStatistics(gameId)).thenReturn(gameStatistics);

        // Mock behavior for GameStatistics
        when(gameStatistics.getGameId()).thenReturn(gameId);
        when(gameStatistics.getRoundsPlayed()).thenReturn(10L);
        when(gameStatistics.getPlayerWins()).thenReturn(5L);
        when(gameStatistics.getAiWins()).thenReturn(4L);
        when(gameStatistics.getDraws()).thenReturn(1L);

        GameStatistics result = gameService.endGame(gameId);

        assertNotNull(result);
        assertEquals(gameId, result.getGameId());
        assertEquals(10L, result.getRoundsPlayed());
        assertEquals(5L, result.getPlayerWins());
        assertEquals(4L, result.getAiWins());
        assertEquals(1L, result.getDraws());

        // Verify interactions with mocks
        verify(gameRepository, times(1)).findById(gameId);
        verify(gameRepository, times(1)).save(game);
        verify(gameRepository, times(1)).getGameStatistics(gameId);
    }

    @Test
    public void testEndGame_GameNotFound() {
        Long gameId = 1L;
        when(gameRepository.findById(gameId)).thenReturn(java.util.Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> gameService.endGame(gameId), "Game not found!");

        assertTrue(thrown.getMessage().contains("Game not found!"));

        verify(gameRepository, times(1)).findById(gameId);
        verify(gameRepository, times(0)).save(any(Game.class));
        verify(gameRepository, times(0)).getGameStatistics(anyLong());
    }
}
