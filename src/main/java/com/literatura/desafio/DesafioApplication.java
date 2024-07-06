package com.literatura.desafio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.literatura.desafio.principal.Principal;
import com.literatura.desafio.repository.AutorRepository;
import com.literatura.desafio.repository.LibroRepository;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repository;

	@Autowired
	private AutorRepository autorRepository;
	public static void main(String[] args)  {
		SpringApplication.run(DesafioApplication.class, args);
	}

	

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, autorRepository);
		principal.mostrarMenu();
	}

}
