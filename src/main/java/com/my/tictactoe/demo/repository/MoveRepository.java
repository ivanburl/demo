package com.my.tictactoe.demo.repository;

import com.my.tictactoe.demo.model.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends JpaRepository<Move, Long> {

}
