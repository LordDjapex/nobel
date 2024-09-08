package com.example.nobel.repository;

import com.example.nobel.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {

//    @Query(value = "SELECT r from Round r WHERE g.gameId = ?1")
//    List<Round> getRoundsByGameId(Long gameId);
}
