package sse.edu.SPR2024;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class Spr2024Application {
//	Test
	public static void main(String[] args) {
		SpringApplication.run(Spr2024Application.class, args);
	}

	//config mapper
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
