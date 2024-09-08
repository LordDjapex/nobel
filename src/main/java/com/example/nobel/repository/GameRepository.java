package com.example.nobel.repository;

import com.example.nobel.model.Game;
import com.example.nobel.model.dto.GameStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "SELECT g.name AS gameName, r.game_id as gameId, COUNT(r.*) AS roundsPlayed, " +
                    "COUNT(case CAST(r.player_result AS integer) WHEN 0 then 1 else null end) as playerWins, " +
                    "COUNT(case CAST(r.player_result AS integer) WHEN 1 then 1 else null end) as aiWins, " +
                    "COUNT(case CAST(r.player_result AS integer) WHEN 2 then 1 else null end) as draws " +
                    "FROM round r JOIN game g on g.id = r.game_id " +
                    "WHERE r.game_id = ?1 GROUP BY r.game_id, g.name", nativeQuery = true)
    GameStatistics getGameStatistics(Long gameId);

    @Query(value = "SELECT g.name AS gameName, game_id as gameId, COUNT(r.*) AS roundsPlayed, " +
            "COUNT(case CAST(r.player_result AS integer) WHEN 0 then 1 else null end) as playerWins, " +
            "COUNT(case CAST(r.player_result AS integer) WHEN 1 then 1 else null end) as aiWins, " +
            "COUNT(case CAST(r.player_result AS integer) WHEN 2 then 1 else null end) as draws " +
            "FROM round r JOIN game g on g.id = r.game_id GROUP BY r.game_id, g.name", nativeQuery = true)
    List<GameStatistics> getGameStatisticsList();

}
