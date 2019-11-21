package br.com.bpd.api.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.bpd")
public class BrasilPrevDigitalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrasilPrevDigitalApiApplication.class, args);
	}

}
