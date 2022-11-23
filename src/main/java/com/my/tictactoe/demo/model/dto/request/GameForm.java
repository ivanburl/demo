package com.my.tictactoe.demo.model.dto.request;

import com.my.tictactoe.demo.model.config.GameConfig;
import com.my.tictactoe.demo.model.dto.connect.PlayerConnect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GameForm {
  private PlayerConnect playerX;
  private PlayerConnect playerO;
  private GameConfig gameConfig;
}
