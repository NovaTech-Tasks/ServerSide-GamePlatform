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

//@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
//		return security.authorizeHttpRequests()
//				.requestMatchers(HttpMethod.POST, "api/v1/users")
//				.permitAll()
//				.anyRequest()
//				.authenticated().and()
//				.csrf().disable()
//				.httpBasic().and()
//				.formLogin().and()
//				.build();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder(){
//		return new PasswordEncoder() {
//			@Override
//			public String encode(CharSequence rawPassword) {
//				return rawPassword.toString();
//			}
//
//			@Override
//			public boolean matches(CharSequence rawPassword, String encodedPassword) {
//				return rawPassword.toString().matches(encodedPassword);
//			}
//		};
//	}

}
