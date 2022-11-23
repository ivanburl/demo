package com.my.tictactoe.demo.repository;

import com.my.tictactoe.demo.model.Player;
import java.util.Optional;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
  boolean existsByUsername(@NotNull @NotBlank String username);
  Optional<Player> findByUsername(@NotNull @NotBlank String username);
}
