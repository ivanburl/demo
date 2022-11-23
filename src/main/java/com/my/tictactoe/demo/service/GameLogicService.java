package com.my.tictactoe.demo.service;

import com.my.tictactoe.demo.model.Game;
import com.my.tictactoe.demo.model.Move;
import com.my.tictactoe.demo.model.config.GameState;
import com.my.tictactoe.demo.model.dto.request.MoveForm;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameLogicService {

  private final ModelMapper mapper;

  public GameState getGameState(@NonNull Game game, @NonNull MoveForm moveForm) {
    var move = mapper.map(moveForm, Move.class);
    game.getMoves().add(move);
    return winnerCheck(game);
  }

  private GameState winnerCheck(@NonNull Game game) {
    var configs = game.getGameConfig();

    if (game.getMoves().size()==configs.getColumnsCount()*configs.getRowsCount())
      return GameState.DRAW;

    var board = simplifyBoard(game);

    for (int r=1;r<=configs.getRowsCount();r++)
      for (int c=1;c<=configs.getColumnsCount();c++) {

        for (int incX=-1;incX<=1;incX++)
          for (int incY=-1;incY<=1;incY++) {
            int sum = 0;
            for (int q=0;q<configs.getInRow();q++) {
              if (1<=r+incY && r+incY<=configs.getColumnsCount() &&
                  1<=c+incX && c+incX<=configs.getRowsCount()) sum+=board[r+incY][c+incX];
            }
            if (sum==configs.getInRow()) return GameState.X_WIN;
            if (sum==-configs.getInRow()) return GameState.O_WIN;
          }
      }

    return GameState.PLAYING;
  }


  private int[][] simplifyBoard(@NonNull Game game) {
    var playerX = game.getPlayerX();
    var configs = game.getGameConfig();

    var board = new int[configs.getInRow()][configs.getColumnsCount()];

    for (var i : game.getMoves()) {
      board[(int) (i.getIndex() / configs.getColumnsCount())][(int) (i.getIndex()
          % configs.getColumnsCount())] =
          i.getPlayer() == playerX ? 1 : -1;
    }
    return board;
  }
}
