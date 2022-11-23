package com.my.tictactoe.demo.security.services.Implementation;

import com.my.tictactoe.demo.exception.IllegalArgumentsException;
import com.my.tictactoe.demo.exception.ResourceNotFoundException;
import com.my.tictactoe.demo.repository.PlayerRepository;
import com.my.tictactoe.demo.security.models.PlayerDetails;
import com.my.tictactoe.demo.security.services.PlayerDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlayerDetailsServiceImpl implements PlayerDetailsService {

  private final PlayerRepository userRepository;

  public PlayerDetailsServiceImpl(PlayerRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("loadingByUsername started with username " + username);
    var player = userRepository.findByUsername(username);
    if (player.isEmpty()) {
      throw new ResourceNotFoundException("Username: " + username + " is not found");
    }
    return new PlayerDetails(player.get());
  }
}
