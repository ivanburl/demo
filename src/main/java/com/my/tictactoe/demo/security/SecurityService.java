package com.my.tictactoe.demo.security;

import com.my.tictactoe.demo.security.models.PlayerDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityService {

  public boolean isAnonymous() {
    return SecurityContextHolder.getContext()
        .getAuthentication() instanceof AnonymousAuthenticationToken;
  }

  public PlayerDetails getPrincipal() {
    if (isAnonymous()) {
      throw new NullPointerException("No correct authentication for such operations");
    }

    var authentication = SecurityContextHolder.getContext().getAuthentication();
    return (PlayerDetails) authentication.getPrincipal();
  }

}
