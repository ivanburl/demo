package com.my.tictactoe.demo.controler;

import com.my.tictactoe.demo.model.dto.response.PlayerResponse;
import com.my.tictactoe.demo.security.SecurityService;
import com.my.tictactoe.demo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TemplateController {
  private final PlayerService playerService;
  private final ModelMapper mapper;

  @GetMapping
  private String hi() {
    return "Server is working!!";
  }

  @GetMapping("/login")
  private PlayerResponse logIn() {
    return mapper.map(playerService.getCurrentPrincipal(), PlayerResponse.class);
  }
}
