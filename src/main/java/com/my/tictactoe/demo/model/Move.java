package com.my.tictactoe.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "moves")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Move {

  @Id
  @GeneratedValue
  public Long Id;

  @ManyToOne
  private Game game;
  @ManyToOne
  private Player player;

  @NotNull
  @Min(1)
  private Long index;
}