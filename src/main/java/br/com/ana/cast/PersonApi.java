package br.com.ana.cast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.ana.cast")
public class PersonApi {
	public static void main(String[] args) {
		SpringApplication.run(PersonApi.class, args);
	}
}