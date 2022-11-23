package com.my.tictactoe.demo.service;

import com.my.tictactoe.demo.exception.ResourceNotFoundException;
import com.my.tictactoe.demo.model.Game;
import com.my.tictactoe.demo.model.Player;
import com.my.tictactoe.demo.model.dto.request.GameForm;
import com.my.tictactoe.demo.model.dto.update.GameUpdate;
import com.my.tictactoe.demo.repository.GameRepository;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class GameService {

  private final GameRepository gameRepository;
  private final ModelMapper mapper;

  public List<Game> findAll() {
    return gameRepository.findAll();
  }

  public Game findById(@NonNull UUID id) {
    return gameRepository.findById(id).orElseThrow(()->{
      throw new ResourceNotFoundException(String.format("Game does not exsit with id %s", id));});
  }

  public Game createGame(@NonNull GameForm gameForm) {
    var game = mapper.map(gameForm, Game.class);
    return gameRepository.save(game);
  }

  public Game putGame(@NonNull GameUpdate gameUpdate) {
    var game = findById(gameUpdate.getGameId());
    var newGame = mapper.map(gameUpdate, Game.class);
    log.info("New "+newGame);
    log.info("Old "+ game);
    if (game.getPlayerX()==null) game.setPlayerX(newGame.getPlayerX());
    if (game.getPlayerO()==null) game.setPlayerO(newGame.getPlayerO());
    log.info("Res "+game);
    return gameRepository.save(game);
  }

  protected void putGame(@NonNull Game game) {
    var origGame = findById(game.getId());
    gameRepository.save(game);
  }
}
