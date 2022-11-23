package com.my.tictactoe.demo.model.dto.request;

import com.my.tictactoe.demo.model.dto.connect.GameConnect;
import com.my.tictactoe.demo.model.dto.connect.PlayerConnect;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class MoveForm {
  private GameConnect game;
  private PlayerConnect player;
  @Min(1)
  private Long index;
}
