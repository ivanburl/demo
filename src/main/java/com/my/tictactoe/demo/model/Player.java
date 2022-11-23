package com.my.tictactoe.demo.model;

import com.my.tictactoe.demo.model.config.Role;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="players")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @ToString
public class Player {

  @Id @GeneratedValue
  private UUID Id;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Role role;

  @NotNull @NotBlank
  private String username;

  @NotNull @NotBlank
  private String password;

  @NotNull @Min(0)
  private Long rating;

  @OneToMany(mappedBy = "playerX")
  private List<Game> gamesX;

  @OneToMany(mappedBy = "playerO")
  private List<Game> gamesO;

  @OneToMany(mappedBy = "player")
  private List<Move> moves;
}
