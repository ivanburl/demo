package com.my.tictactoe.demo.security;

import com.my.tictactoe.demo.exception.ResourceNotFoundException;
import com.my.tictactoe.demo.model.config.Role;
import com.my.tictactoe.demo.security.models.PlayerDetails;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityService {

  public boolean isAnonymous() {
    return !SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
  }

  public PlayerDetails getPrincipal(Authentication authentication) {
    if (!authentication.isAuthenticated()) {
      log.error("Can get player details due to the anonymous");
      throw new ResourceNotFoundException("No user authenticated!");
    }
    return (PlayerDetails) authentication.getPrincipal();
  }

  public PlayerDetails getPrincipal() {
    return getPrincipal(SecurityContextHolder.getContext().getAuthentication());
  }
}
