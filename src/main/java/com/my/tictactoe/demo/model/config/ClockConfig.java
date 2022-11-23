package com.my.tictactoe.demo.model.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClockConfig {
  LOL(10,10);

  private final Integer startTimeInSecs;
  private final Integer addTimeInSecs;
}
