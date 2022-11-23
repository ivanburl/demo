package com.my.tictactoe.demo.controler;

import com.my.tictactoe.demo.model.dto.request.MoveForm;
import com.my.tictactoe.demo.model.dto.response.MoveResponse;
import com.my.tictactoe.demo.service.MoveService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("api/moves")
@RequiredArgsConstructor
public class MoveController {

  private final ModelMapper mapper;
  private final MoveService moveService;

  @GetMapping
  public List<MoveResponse> getAll() {
    return moveService.findAll().stream().map((m) -> mapper.map(m, MoveResponse.class)).collect(
        Collectors.toList());
  }

  @GetMapping(value = "/{id}")
  public MoveResponse getById(@PathVariable Long id) {
    return mapper.map(moveService.findById(id), MoveResponse.class);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MoveResponse createGame(@RequestBody MoveForm moveForm) {
    return mapper.map(moveService.createMove(moveForm), MoveResponse.class);
  }
}
