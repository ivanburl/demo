package com.my.tictactoe.demo.controler;

import com.my.tictactoe.demo.model.Player;
import com.my.tictactoe.demo.model.config.Role;
import com.my.tictactoe.demo.model.dto.response.PlayerResponse;
import com.my.tictactoe.demo.security.SecurityService;
import com.my.tictactoe.demo.security.models.PlayerDetails;
import com.my.tictactoe.demo.service.PlayerService;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

@RestController
@RequestMapping("api/security")
@RequiredArgsConstructor
public class SecurityController {
  private final SecurityService securityService;
  private final PlayerService playerService;
  private final JwtEncoder jwtEncoder;
  private final ModelMapper mapper;


  @GetMapping("/login")
  private String logIn(Authentication authentication) {
    Instant now = Instant.now();
    long expiry = 60*60*3L;//3 hours validity

    String scope = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));

    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("self")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiry))
        .subject(authentication.getName())
        .claim("scope", scope)
        .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  @GetMapping("/currentPlayer")
  private PlayerResponse getCurrent(Authentication authentication) {
     var player = securityService.getPrincipal(authentication);
     var origPlayer = playerService.findById(player.getPlayer().getId());
     return mapper.map(origPlayer, PlayerResponse.class);
  }

  @GetMapping("/logout")
  private void logOut(Authentication authentication) {
//    var player = securityService.getPrincipal(authentication);
//    var origPlayer = playerService.findById(player.getPlayer().getId());
//    return mapper.map(origPlayer, PlayerResponse.class);
  }

  //TODO log out???
}
