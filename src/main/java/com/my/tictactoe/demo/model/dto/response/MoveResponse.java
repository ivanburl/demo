package com.my.tictactoe.demo.model.dto.response;

import com.my.tictactoe.demo.model.dto.connect.GameConnect;
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
public class MoveResponse {
  private  Long id;
  private  GameConnect game;
  private  PlayerConnect player;
  private  Long index;
}
