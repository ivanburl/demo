package com.my.tictactoe.demo.model.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameConfig {
  DEFAULT(3,3,3),
  SIMPLE(5,5, 5),
  MIDDLE(10,10, 5),
  HARD(15,15, 5);

  final int rowsCount;
  final int columnsCount;
  final int inRow;
}
