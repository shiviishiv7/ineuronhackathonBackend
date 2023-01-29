package com.hack.hackathon;

import com.twilio.Twilio;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

//@CrossOrigin(value = "*")
@SpringBootApplication
@SpringBootConfiguration
public class HackathonApplication {
	@PostConstruct
	public void initTwilio(){
		Twilio.init("AC3623e108d0977f3dc339ff9f7330e558","089890afba8079b85b75b151a5676bad");
	}


//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET","POST","OPTIONS","PUT","DELETE");
//			}
//		};
//	}
//

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(HackathonApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
}
