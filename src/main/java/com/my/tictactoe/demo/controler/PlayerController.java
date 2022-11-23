package com.my.tictactoe.demo.controler;

import com.my.tictactoe.demo.model.dto.request.PlayerForm;
import com.my.tictactoe.demo.model.dto.response.PlayerResponse;
import com.my.tictactoe.demo.security.SecurityService;
import com.my.tictactoe.demo.service.PlayerService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/players")
@RequiredArgsConstructor
@Slf4j
public class PlayerController {

  private final PlayerService playerService;

  private final SecurityService securityService;
  private final ModelMapper mapper;

  @GetMapping("/current")
  public PlayerResponse getCurrentUser() {
    return mapper.map(playerService.getCurrentPrincipal(), PlayerResponse.class);
  }

  @GetMapping
  public List<PlayerResponse> getAll() {
    return playerService.findAll().stream().map((p)->mapper.map(p, PlayerResponse.class)).collect(
        Collectors.toList());
  }

  @GetMapping(value = "/{id}")
  public PlayerResponse getById(@PathVariable UUID id) {
    return mapper.map(playerService.findById(id), PlayerResponse.class);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PlayerResponse createPlayer(@RequestBody PlayerForm playerForm) {
    return mapper.map(playerService.createPlayer(playerForm), PlayerResponse.class);
  }
}
