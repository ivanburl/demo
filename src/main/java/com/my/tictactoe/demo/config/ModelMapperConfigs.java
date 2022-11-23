package com.my.tictactoe.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfigs {
  @Bean
  public ModelMapper getModelMapper() {
    return new ModelMapper();
  }
}
