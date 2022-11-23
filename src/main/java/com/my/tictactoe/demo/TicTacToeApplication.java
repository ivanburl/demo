package com.my.tictactoe.demo;

import com.my.tictactoe.demo.model.Player;
import com.my.tictactoe.demo.model.config.Role;
import com.my.tictactoe.demo.model.dto.request.PlayerForm;
import com.my.tictactoe.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicTacToeApplication {
	private final String mainAdminUsername = "admin";

	private final String mainAdminPassword = "admin";

	public static void main(String[] args) {
		SpringApplication.run(TicTacToeApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(PlayerService playerService) {
		return (String[] args) -> {
			var admin = new PlayerForm(Role.ADMIN, mainAdminUsername, mainAdminPassword, 1000L);
			playerService.createPlayer(admin);
		};
	}//TODO fix when admin exists???

}
