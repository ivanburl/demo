package com.my.tictactoe.demo.controler;

import com.my.tictactoe.demo.model.dto.request.GameForm;
import com.my.tictactoe.demo.model.dto.response.GameResponse;
import com.my.tictactoe.demo.model.dto.update.GameUpdate;
import com.my.tictactoe.demo.service.GameService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/games")
@RequiredArgsConstructor
public class GameController {

  private final GameService gameService;
  private final ModelMapper mapper;

  @GetMapping
  public List<GameResponse> getAll() {
    return gameService.findAll().stream().map((g)->mapper.map(g, GameResponse.class)).collect(Collectors.toList());
  }

  @GetMapping(value = "/{id}")
  public GameResponse getById(@PathVariable UUID id) {
    return mapper.map(gameService.findById(id), GameResponse.class);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GameResponse createGame(@RequestBody GameForm gameForm) {
    return mapper.map(gameService.createGame(gameForm), GameResponse.class);
  }

  @PutMapping
  public GameResponse putGame(@RequestBody GameUpdate gameUpdate) {
    return mapper.map(gameService.putGame(gameUpdate), GameResponse.class);
  }
}
