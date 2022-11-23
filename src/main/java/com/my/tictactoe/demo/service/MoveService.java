package com.my.tictactoe.demo.service;

import com.my.tictactoe.demo.exception.IllegalArgumentsException;
import com.my.tictactoe.demo.exception.ResourceNotFoundException;
import com.my.tictactoe.demo.model.Move;
import com.my.tictactoe.demo.model.dto.request.MoveForm;
import com.my.tictactoe.demo.repository.MoveRepository;
import com.my.tictactoe.demo.security.SecurityService;
import java.util.List;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MoveService {

  private final MoveRepository moveRepository;
  private final GameService gameService;
  private final GameLogicService gameLogicService;
  private final ModelMapper modelMapper;

  private final PlayerService playerService;

  public List<Move> findAll() {
    return moveRepository.findAll();
  }

  public Move findById(@NonNull Long id) {
    return moveRepository.findById(id).orElseThrow(() -> {
      throw new ResourceNotFoundException(String.format("Move does not exist with id %s", id));
    });
  }

  public Move createMove(@NonNull MoveForm moveForm) {

    var game = gameService.findById(moveForm.getGame().getGameId());

    game.getMoves().stream()
        .filter((m) -> Objects.equals(moveForm.getIndex(), m.getIndex()))
        .findAny().orElseThrow(() -> {
          throw new IllegalArgumentsException(
              String.format("Move on index %s has been already made", moveForm.getIndex()));
        });

    if (game.getMoves().size()%2==0) {
      if (!playerService.getCurrentPrincipal().getId().equals(game.getPlayerX().getId())) {
        throw new IllegalArgumentsException("Now turn of X player");
      }
    } else
    {
      if (!playerService.getCurrentPrincipal().getId().equals(game.getPlayerO().getId())) {
        throw new IllegalArgumentsException("Now turn of O player");
      }
    }

    game.setState(gameLogicService.getGameState(game, moveForm));

    gameService.putGame(game);

    var move = modelMapper.map(moveForm, Move.class);
    return moveRepository.save(move);
  }
}
