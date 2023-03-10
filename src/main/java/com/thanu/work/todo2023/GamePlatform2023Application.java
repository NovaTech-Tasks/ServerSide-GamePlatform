package com.thanu.work.todo2023;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class GamePlatform2023Application {
@Bean
    public ModelMapper modelMapper(){
	return new ModelMapper();

}
	public static void main(String[] args) {
		SpringApplication.run(GamePlatform2023Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfig(){
	  return new WebMvcConfigurer() {
		  @Override
		  public void addCorsMappings(CorsRegistry registry) {
			 registry.addMapping("/**").allowedOrigins("http://localhost:4200");
		  }
	  };
	}

}
