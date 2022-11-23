package com.my.tictactoe.demo.model.dto.response;

import com.my.tictactoe.demo.model.config.Role;
import com.my.tictactoe.demo.model.dto.connect.GameConnect;
import com.my.tictactoe.demo.model.dto.connect.MoveConnect;
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
public class PlayerResponse {
  private  UUID id;
  private  String username;
  private  Long rating;
  private  Role role;
  private  List<GameConnect> gamesX;
  private  List<GameConnect> gamesO;
  private  List<MoveConnect> moves;
}
