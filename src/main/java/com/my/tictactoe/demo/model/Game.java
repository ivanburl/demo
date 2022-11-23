package com.my.tictactoe.demo.model;

import com.my.tictactoe.demo.model.config.GameConfig;
import com.my.tictactoe.demo.model.config.GameState;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "games")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Game {
  @Id @GeneratedValue
  private UUID Id;

  @ManyToOne
  private Player playerX;

  @ManyToOne
  private Player playerO;

  @NotNull @Enumerated(EnumType.STRING)
  private GameConfig gameConfig;

  @OneToMany(mappedBy = "game")
  private List<Move> moves;

  @Enumerated(EnumType.STRING)
  private GameState state;
}
