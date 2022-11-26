package com.my.tictactoe.demo.service;

import com.my.tictactoe.demo.exception.ResourceAlreadyExistsException;
import com.my.tictactoe.demo.exception.ResourceNotFoundException;
import com.my.tictactoe.demo.model.Player;
import com.my.tictactoe.demo.model.dto.request.PlayerForm;
import com.my.tictactoe.demo.repository.PlayerRepository;
import com.my.tictactoe.demo.security.SecurityService;
import java.util.List;
import java.util.UUID;
import javax.annotation.security.RolesAllowed;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class PlayerService {
  private final PlayerRepository playerRepository;
  private final PasswordEncoder passwordEncoder;

  private final SecurityService securityService;
  private final ModelMapper mapper;

  public List<Player> findAll() {
    return playerRepository.findAll();
  }

  public Player findById(@NonNull UUID id) {
    return playerRepository.findById(id).orElseThrow(()->{
      throw new ResourceNotFoundException(String.format("Player does nor exist with %s", id));});
  }


  public Player createPlayer(@NonNull PlayerForm playerForm) {

    if (playerRepository.existsByUsername(playerForm.getUsername())) {
      throw new ResourceAlreadyExistsException(
          String.format("Player with nickname %s exists", playerForm.getUsername()));
    }

    var player = mapper.map(playerForm, Player.class);
    log.info(""+player);
    player.setPassword(passwordEncoder.encode(player.getPassword()));

    return playerRepository.save(player);
  }
}
