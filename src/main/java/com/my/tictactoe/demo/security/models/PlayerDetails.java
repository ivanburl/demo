package com.my.tictactoe.demo.security.models;

import com.my.tictactoe.demo.model.Player;
import com.my.tictactoe.demo.model.config.Role;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
@Getter
public class PlayerDetails implements UserDetails {

  private final Player player;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    var authorities = new ArrayList<SimpleGrantedAuthority>();

    switch (player.getRole()) {
      case ADMIN:
        authorities.add(new SimpleGrantedAuthority("ROLE_" + Role.ADMIN.name()));
      case PLAYER:
        authorities.add(new SimpleGrantedAuthority("ROLE_" + Role.PLAYER.name()));
    }

    return authorities;
  }

  @Override
  public String getPassword() {
    return player.getPassword();
  }

  @Override
  public String getUsername() {
    return player.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
