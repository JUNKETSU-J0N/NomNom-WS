package com.nomnom.nnws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.nomnom.nnws.project.repository")
public class NomNomWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NomNomWsApplication.class, args);
	}

}
