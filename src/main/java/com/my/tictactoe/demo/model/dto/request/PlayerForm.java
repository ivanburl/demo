package com.my.tictactoe.demo.model.dto.request;

import com.my.tictactoe.demo.model.config.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PlayerForm {
  private  Role role;
  private  String username;
  private  String password;
  private  Long rating;
}
