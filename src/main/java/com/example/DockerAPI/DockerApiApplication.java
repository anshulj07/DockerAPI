package com.example.DockerAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DockerApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DockerApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application Started");
	}

}
