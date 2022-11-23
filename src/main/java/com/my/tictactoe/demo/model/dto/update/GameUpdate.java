package com.my.tictactoe.demo.model.dto.update;

import com.my.tictactoe.demo.model.dto.connect.PlayerConnect;
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
public class GameUpdate {
  private UUID gameId;
  private PlayerConnect playerX;
  private PlayerConnect playerO;
}
