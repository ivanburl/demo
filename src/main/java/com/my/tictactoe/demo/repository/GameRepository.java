package com.my.tictactoe.demo.repository;

import com.my.tictactoe.demo.model.Game;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {

}
