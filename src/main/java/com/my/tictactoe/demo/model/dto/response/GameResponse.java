package com.my.tictactoe.demo.model.dto.response;

import com.my.tictactoe.demo.model.config.GameConfig;
import com.my.tictactoe.demo.model.config.GameState;
import com.my.tictactoe.demo.model.dto.connect.MoveConnect;
import com.my.tictactoe.demo.model.dto.connect.PlayerConnect;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GameResponse {
  private  UUID gameId;
  private  GameConfig gameConfig;
  private  PlayerConnect playerX;
  private  PlayerConnect playerO;
  private  List<MoveConnect> moves;
  private  GameState state;
}
